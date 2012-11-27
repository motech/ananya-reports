package org.motechproject.ananya.reports.web.kilkari.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriptionResponse;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.reports.kilkari.service.SubscriberService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.reports.web.kilkari.controller.mapper.SubscriberMapper;
import org.motechproject.ananya.reports.web.kilkari.controller.mapper.SubscriptionResponseMapper;
import org.motechproject.ananya.reports.web.util.HttpConstants;
import org.motechproject.ananya.reports.web.util.TestUtils;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.ananya.reports.web.util.MVCTestUtils.mockMvc;
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
                "\"subscriptionStatus\":\"NEW\",\n" +
                "\"reason\":\"some reason\"\n" +
                "}";

        mockMvc(subscriptionController)
                .perform(post("/subscription")
                        .body(subscriptionRequestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        ArgumentCaptor<SubscriptionReportRequest> subscriptionRequestArgumentCaptor = ArgumentCaptor.forClass(SubscriptionReportRequest.class);
        verify(subscriptionStatusMeasureService).createSubscription(subscriptionRequestArgumentCaptor.capture());

        SubscriptionReportRequest subscriptionReportRequest = subscriptionRequestArgumentCaptor.getValue();
        assertEquals(Long.valueOf(9740123123L), subscriptionReportRequest.getMsisdn());
        assertEquals("sid", subscriptionReportRequest.getSubscriptionId());
        assertEquals("some reason", subscriptionReportRequest.getReason());

    }

    @Test
    public void shouldUpdateSubscriptionForStateChange() throws Exception {

        String subscriptionStateChangeRequestJson = "{\n" +
                "\"subscriptionId\":\"sid\",\n" +
                "\"subscriptionStatus\":\"ACTIVE\",\n" +
                "\"createdAt\":\"2010-05-05\",\n" +
                "\"graceCount\":7,\n" +
                "\"reason\":\"some reason\"\n" +
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
        assertEquals("some reason", subscriptionStateChangeRequest.getReason());
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
        String pack = "BARI_KILKARI";
        int weekNumber = 13;
        int startWeekNumber = 33;
        Subscriber subscriber = new Subscriber(name, 23, edd, dob, null, new LocationDimension(district, block, panchayat, "VALID"), null, null, startWeekNumber);
        Subscription subscription = new Subscription(Long.parseLong(msisdn), subscriber, new SubscriptionPackDimension(pack), null, null, null, subscriptionId, DateTime.now(), DateTime.now(), status, weekNumber, null);

        final SubscriptionResponse expectedResponse = SubscriptionResponseMapper.mapFrom(subscription);
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
        Integer beneficiaryAge = 24;
        String subscriberRequestJson = TestUtils.toJson(new SubscriberReportRequest(createdAt, beneficiaryName, beneficiaryAge, location));
        SubscriberReportRequest expectedSubscriberReportRequest = TestUtils.fromJson(subscriberRequestJson, SubscriberReportRequest.class);

        mockMvc(subscriptionController)
                .perform(put("/subscriber/" + expectedSubscriptionId)
                        .body(subscriberRequestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        ArgumentCaptor<SubscriberReportRequest> subscriberRequestArgumentCaptor = ArgumentCaptor.forClass(SubscriberReportRequest.class);
        ArgumentCaptor<String> subscriptionIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(subscriberService).update(subscriberRequestArgumentCaptor.capture(), subscriptionIdArgumentCaptor.capture());
        SubscriberReportRequest actualSubscriberReportRequest = subscriberRequestArgumentCaptor.getValue();
        String actualSubscriptionId = subscriptionIdArgumentCaptor.getValue();

        assertEquals(expectedSubscriptionId, actualSubscriptionId);
        assertEquals(expectedSubscriberReportRequest, actualSubscriberReportRequest);
    }

    @Test
    public void shouldReturnSubscriberDetailsForAGivenSubscriptionId() throws Exception {
        String msisdn = "1234567890";
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String subscriptionId = "subscriptionId";
        String name = "name";
        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        String status = "ACTIVE";
        String pack = "BARI_KILKARI";
        int weekNumber = 13;
        Subscriber subscriber = new Subscriber(name, 23, edd, dob, null,
                new LocationDimension(district, block, panchayat, "VALID"), null, null, null);
        Subscription subscription = new Subscription(Long.parseLong(msisdn), subscriber, new SubscriptionPackDimension(pack), null, null,
                null, subscriptionId, DateTime.now(), DateTime.now(), status, weekNumber, null);

        final SubscriberResponse expectedResponse = SubscriberMapper.mapFrom(subscription);
        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(subscription);

        mockMvc(subscriptionController)
                .perform(get("/subscription/subscriber/" + subscriptionId)).andExpect(status().isOk())
                .andExpect(content().type(HttpConstants.RESPONSE_JSON))
                .andExpect(content().string(assertSubscriberResponse(expectedResponse)));
    }

    @Test
    public void shouldChangeMsisdnForSubscriptionId() throws Exception {
        String msisdn = "1234567890";
        Long msisdnAsLong = 1234567890L;
        String subscriptionId = "subscriptionId";

        mockMvc(subscriptionController)
                .perform(post("/subscription/changemsisdn")
                        .param("subscriptionId", subscriptionId).param("msisdn", msisdn)).andExpect(status().isOk())
                .andExpect(status().isOk());

        verify(subscriptionService).changeMsisdnForSubscription(subscriptionId, msisdnAsLong);
    }

    private BaseMatcher<String> assertSubscriberResponse(final SubscriberResponse expectedResponse) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return TestUtils.toJson(expectedResponse).equals((String) o);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
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

