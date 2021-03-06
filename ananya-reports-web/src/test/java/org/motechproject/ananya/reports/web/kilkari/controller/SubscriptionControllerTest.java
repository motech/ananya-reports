package org.motechproject.ananya.reports.web.kilkari.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.*;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.domain.MessageCampaignPack;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.reports.kilkari.service.CampaignScheduleAlertService;
import org.motechproject.ananya.reports.kilkari.service.SubscriberService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.reports.web.kilkari.controller.mapper.SubscriberResponseMapper;
import org.motechproject.ananya.reports.web.util.HttpConstants;
import org.motechproject.ananya.reports.web.util.TestUtils;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.eq;
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
    @Mock
    private CampaignScheduleAlertService campaignScheduleAlertService;

    @Before
    public void setUp() {
        initMocks(this);
        subscriptionController = new SubscriptionController(subscriptionStatusMeasureService, subscriptionService, subscriberService, campaignScheduleAlertService);
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

        String referredByFLWMsisdn = "9876543210";
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String subscriptionId = "subscriptionId";
        String name = "name";
        String state = "S1";
        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        String status = "ACTIVE";
        String pack = "BARI_KILKARI";

        int startWeekNumber = 33;
        Subscriber subscriber = new Subscriber(name, 23, edd, dob, null, new LocationDimension(state, district, block, panchayat, "VALID"), null, null, startWeekNumber, DateTime.now());
        Subscription subscription = new Subscription(Long.parseLong(msisdn), subscriber, new SubscriptionPackDimension(pack), null, null, null, subscriptionId, DateTime.now(), DateTime.now(), status, null, referredByFLWMsisdn, true);

        final SubscriberResponse expectedResponse = SubscriberResponseMapper.mapFrom(subscription);
        List<SubscriberResponse> expectedReponseList = new ArrayList<SubscriberResponse>() {{
            add(expectedResponse);
        }};
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(subscription);
        when(subscriptionService.findByMsisdn(msisdn)).thenReturn(subscriptions);

        mockMvc(subscriptionController)
                .perform(get("/subscriber").param("msisdn", msisdn)).andExpect(status().isOk())
                .andExpect(content().type(HttpConstants.RESPONSE_JSON))
                .andExpect(content().string(assertSubscriberResponse(expectedReponseList)));
    }

    @Test
    public void shouldInvokeSubscriberServiceToUpdateSubscriberDetails() throws Exception {
        String expectedSubscriptionId = "sid";
        String state = "s1";
        String district = "d1";
        String block = "b1";
        String panchayat = "p1";
        SubscriberLocation location = new SubscriberLocation(state, district, block, panchayat);
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

        String referredByFLWMsisdn = "9876543210";

        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String subscriptionId = "subscriptionId";
        String name = "name";

        String state = "S1";

        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        String status = "ACTIVE";
        String pack = "BARI_KILKARI";
        Subscriber subscriber = new Subscriber(name, 23, edd, dob, null,
                new LocationDimension(state, district, block, panchayat, "VALID"), null, null, null, DateTime.now());
        Subscription subscription = new Subscription(Long.parseLong(msisdn), subscriber, new SubscriptionPackDimension(pack), null, null,
                null, subscriptionId, DateTime.now(), DateTime.now(), status, null, referredByFLWMsisdn, true);


        final SubscriberResponse expectedResponse = SubscriberResponseMapper.mapFrom(subscription);
        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(subscription);

        mockMvc(subscriptionController)
                .perform(get("/subscription/subscriber/" + subscriptionId)).andExpect(status().isOk())
                .andExpect(content().type(HttpConstants.RESPONSE_JSON))
                .andExpect(content().string(assertSubscriberResponse(expectedResponse)));
    }

    @Test
    public void shouldChangeMsisdnForSubscriptionId() throws Exception {
        Long msisdnAsLong = 1234567890L;
        String subscriptionId = "subscriptionId";

        SubscriberChangeMsisdnReportRequest subscriberChangeMsisdnReportRequest = TestUtils.fromJson(TestUtils.toJson(new SubscriberChangeMsisdnReportRequest(subscriptionId, msisdnAsLong, "reason", DateTime.now())), SubscriberChangeMsisdnReportRequest.class);
        mockMvc(subscriptionController)
                .perform(post("/subscription/changemsisdn")
                        .body(TestUtils.toJson(subscriberChangeMsisdnReportRequest).getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(subscriptionStatusMeasureService).changeMsisdnForNewEarlySubscription(subscriberChangeMsisdnReportRequest);
    }

    @Test
    public void shouldCreateCampaignScheduleAlert() throws Exception {
        String subscriptionId = "subscriptionId";
        String campaignId = "campaignId";
        DateTime scheduledAt = DateTime.now();
        CampaignScheduleAlertRequest scheduleAlertRequest
                = new CampaignScheduleAlertRequest(subscriptionId, campaignId, scheduledAt);
        String scheduleAlertRequestJSON = TestUtils.toJson(scheduleAlertRequest);

        mockMvc(subscriptionController)
                .perform(post("/subscription/campaignScheduleAlert")
                        .body(scheduleAlertRequestJSON.getBytes())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<CampaignScheduleAlertRequest> captor = ArgumentCaptor.forClass(CampaignScheduleAlertRequest.class);
        verify(campaignScheduleAlertService).createCampaignScheduleAlert(captor.capture());
        CampaignScheduleAlertRequest campaignScheduleAlertRequest = captor.getValue();
        assertEquals(campaignId, campaignScheduleAlertRequest.getCampaignId());
        assertEquals(subscriptionId, campaignScheduleAlertRequest.getSubscriptionId());
        assertEquals(scheduledAt.getMillis(), campaignScheduleAlertRequest.getScheduledTime().getMillis());
    }

    @Test
    public void shouldUpdateTheCampaign() throws Exception {
        String subscriptionId = "subscriptionId";
        CampaignChangeReportRequest request = new CampaignChangeReportRequest(MessageCampaignPack.INFANT_DEATH.name(), DateTime.now());

        mockMvc(subscriptionController)
                .perform(put("/subscription/" + subscriptionId + "/changecampaign")
                        .body(TestUtils.toJson(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ArgumentCaptor<CampaignChangeReportRequest> argumentCaptor = ArgumentCaptor.forClass(CampaignChangeReportRequest.class);
        verify(subscriptionService).updateMessageCampaign(argumentCaptor.capture(), eq(subscriptionId));
        CampaignChangeReportRequest actualRequest = argumentCaptor.getValue();
        assertEquals(request.getMessageCampaignPack(), actualRequest.getMessageCampaignPack());
        assertEquals(request.getCreatedAt().getMillis(), actualRequest.getCreatedAt().getMillis());
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

    private BaseMatcher<String> assertSubscriberResponse(final List<SubscriberResponse> expectedReponseList) {
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

    @Test
    public void shouldChangeReferredByFLWMsisdnForSubscriptionId() throws Exception {
        String referredByFLWMsisdn = "1234567890";
        String subscriptionId = "subscriptionId";

        SubscriptionChangeReferredFLWMsisdnReportRequest subscriptionChangeReferredFLWMsisdnReportRequest = TestUtils.fromJson(TestUtils.toJson(new SubscriptionChangeReferredFLWMsisdnReportRequest(subscriptionId, referredByFLWMsisdn, "reason", DateTime.now(), true)), SubscriptionChangeReferredFLWMsisdnReportRequest.class);
        mockMvc(subscriptionController)
                .perform(post("/subscription/changereferredmsisdn")
                        .body(TestUtils.toJson(subscriptionChangeReferredFLWMsisdnReportRequest).getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(subscriptionStatusMeasureService).changeReferredByFLWMsisdnForSubscription(subscriptionChangeReferredFLWMsisdnReportRequest);
    }
}

