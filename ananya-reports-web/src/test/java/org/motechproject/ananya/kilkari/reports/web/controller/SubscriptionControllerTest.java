package org.motechproject.ananya.kilkari.reports.web.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriberRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.kilkari.reports.service.SubscriberService;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionService;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.kilkari.reports.web.mapper.SubscriptionMapper;
import org.motechproject.ananya.kilkari.reports.web.response.SubscriptionResponse;
import org.motechproject.ananya.kilkari.reports.web.util.HttpConstants;
import org.motechproject.ananya.kilkari.reports.web.util.TestUtils;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.ananya.kilkari.reports.web.util.MVCTestUtils.mockMvc;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class SubscriptionControllerTest {

    private SubscriptionController subscriptionController;

    @Mock
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private SubscriberService subscriberService;

    @Before
    public void setUp() {
        initMocks(this);
        subscriptionController = new SubscriptionController(subscriptionStatusMeasureService, subscriptionService, subscriberService);
    }

    @Test
    public void shouldCreateSubscription() throws Exception {

        String subscriptionRequestJson = "{\n" +
                "\"subscriptionId\":\"sid\",\n" +
                "\"channel\":\"IVR\",\n" +
                "\"msisdn\":\"9740123123\",\n" +
                "\"pack\":\"PCK1\",\n" +
                "\"name\":\"Sukamma\",\n" +
                "\"ageOfBeneficiary\":24,\n" +
                "\"edd\":\"2010-05-05\", \n" +
                "\"dob\":\"2010-05-05\",\n" +
                "\"createdAt\":\"2010-05-05\",\n" +
                "\"location\":{\n" +
                "\"district\":\"Patna\",\n" +
                "\"block\":\"Dulhin Bazar\",\n" +
                "\"panchayat\":\"Rajipur\"\n" +
                "},\n" +
                "\"operator\":\"airtel\",\n" +
                "\"subscriptionStatus\":\"NEW\"\n" +
                "}";

        mockMvc(subscriptionController)
                .perform(post("/subscription")
                        .body(subscriptionRequestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        ArgumentCaptor<SubscriptionRequest> subscriptionRequestArgumentCaptor = ArgumentCaptor.forClass(SubscriptionRequest.class);
        verify(subscriptionStatusMeasureService).create(subscriptionRequestArgumentCaptor.capture());

        SubscriptionRequest subscriptionRequest = subscriptionRequestArgumentCaptor.getValue();
        assertEquals(9740123123L, subscriptionRequest.getMsisdn());
        assertEquals("sid", subscriptionRequest.getSubscriptionId());
    }

    @Test
    public void shouldUpdateSubscriptionForStateChange() throws Exception {

        String subscriptionStateChangeRequestJson = "{\n" +
                "\"subscriptionId\":\"sid\",\n" +
                "\"subscriptionStatus\":\"ACTIVE\",\n" +
                "\"createdAt\":\"2010-05-05\",\n" +
                "\"graceCount\":7\n" +
                "}";

        mockMvc(subscriptionController)
                .perform(put("/subscription/sid")
                        .body(subscriptionStateChangeRequestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        ArgumentCaptor<SubscriptionStateChangeRequest> subscriptionStateChangeRequestArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStateChangeRequest.class);
        verify(subscriptionStatusMeasureService).update(subscriptionStateChangeRequestArgumentCaptor.capture());

        SubscriptionStateChangeRequest subscriptionStateChangeRequest = subscriptionStateChangeRequestArgumentCaptor.getValue();
        assertEquals("sid", subscriptionStateChangeRequest.getSubscriptionId());
        assertEquals("ACTIVE", subscriptionStateChangeRequest.getSubscriptionStatus());
        assertEquals((Integer) 7, subscriptionStateChangeRequest.getGraceCount());
    }

    @Test
    public void shouldReturnSubscriptionAndSubscriberDetails() throws Exception {
        String msisdn = "1234567890";
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String subscriptionId = "subscriptionId";
        String name = "name";
        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        String status = "ACTIVE";
        String pack = "FIFTEEN_MONTHS";
        int weekNumber = 13;
        Subscriber subscriber = new Subscriber(Long.valueOf(msisdn), name, 23, edd, dob, null, new LocationDimension(district, block, panchayat), null, null);
        Subscription subscription = new Subscription(subscriber, new SubscriptionPackDimension(pack), null, null, null, null, subscriptionId, DateTime.now(), status, weekNumber);

        final SubscriptionResponse expectedResponse = SubscriptionMapper.mapFrom(subscription);
        List<SubscriptionResponse> expectedReponseList = new ArrayList<SubscriptionResponse>() {{
            add(expectedResponse);
        }};
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(subscription);
        when(subscriptionService.findByMsisdn(msisdn)).thenReturn(subscriptions);

        mockMvc(subscriptionController)
                .perform(get("/subscriber").param("msisdn", msisdn)).andExpect(status().isOk())
                .andExpect(content().type(HttpConstants.RESPONSE_JSON))
                .andExpect(content().string(assertSubscriptionResponse(expectedReponseList)));
    }

    @Test
    public void shouldInvokeSubscriberServiceToUpdateSubscriberDetails() throws Exception {
        String expectedSubscriptionId = "sid";
        String district = "d1";
        String block = "b1";
        String panchayat = "p1";
        SubscriberLocation location = new SubscriberLocation(district, block, panchayat);
        DateTime createdAt = DateTime.now();
        String beneficiaryName = "name";
        int beneficiaryAge = 24;
        DateTime expectedDateOfDelivery = DateTime.now().plusMonths(1);
        DateTime dateOfBirth = DateTime.now().minusYears(10);
        String subscriberRequestJson = TestUtils.toJson(new SubscriberRequest(createdAt, beneficiaryName, beneficiaryAge, expectedDateOfDelivery, dateOfBirth, location));
        SubscriberRequest expectedSubscriberRequest = TestUtils.fromJson(subscriberRequestJson, SubscriberRequest.class);

        mockMvc(subscriptionController)
                .perform(put("/subscriber/" + expectedSubscriptionId)
                        .body(subscriberRequestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        ArgumentCaptor<SubscriberRequest> subscriberRequestArgumentCaptor = ArgumentCaptor.forClass(SubscriberRequest.class);
        ArgumentCaptor<String> subscriptionIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(subscriberService).update(subscriberRequestArgumentCaptor.capture(), subscriptionIdArgumentCaptor.capture());
        SubscriberRequest actualSubscriberRequest = subscriberRequestArgumentCaptor.getValue();
        String actualSubscriptionId = subscriptionIdArgumentCaptor.getValue();

        assertEquals(expectedSubscriptionId, actualSubscriptionId);
        assertEquals(expectedSubscriberRequest, actualSubscriberRequest);
    }

    private BaseMatcher<String> assertSubscriptionResponse(final List<SubscriptionResponse> expectedReponseList) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return TestUtils.toJson(expectedReponseList).equals((String) o);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}

