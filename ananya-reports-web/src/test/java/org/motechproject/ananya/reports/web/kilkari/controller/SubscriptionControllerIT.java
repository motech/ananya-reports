package org.motechproject.ananya.reports.web.kilkari.controller;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignScheduleAlertRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberChangeMsisdnReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.motechproject.ananya.reports.web.SpringIntegrationTest;
import org.motechproject.ananya.reports.web.util.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.MvcResult;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.motechproject.ananya.reports.web.util.MVCTestUtils.mockMvc;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class SubscriptionControllerIT extends SpringIntegrationTest {

    @Autowired
    private SubscriptionController subscriptionController;

    @Autowired
    private AllSubscriptions allSubscriptions;

    @Autowired
    private AllDateDimensions allDateDimensions;

    String APPLICATION_JSON = "application/json;charset=UTF-8";

    String msisdn;
    String subscriptionId;
    ChannelDimension channelDimension;
    DateDimension dateDimension;
    Subscriber subscriber;
    Subscription subscription;
    SubscriptionPackDimension subscriptionPackDimension;
    LocationDimension locationDimension;

    private void createSubscriptionForTest() {
        msisdn = "1234567890";
        subscriptionId = "subscriptionId";
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String name = "name";
        String status = "ACTIVE";
        String pack = "BARI_KILKARI";

        channelDimension = new ChannelDimension("channel");
        dateDimension = allDateDimensions.fetchFor(DateTime.now());
        locationDimension = new LocationDimension("disctrict", "block", "panchayat", "VALID");
        subscriber = new Subscriber(name, 23, edd, dob, channelDimension,
                locationDimension, dateDimension, null, null, DateTime.now());
        subscriptionPackDimension = new SubscriptionPackDimension(pack);
        subscription = new Subscription(Long.parseLong(msisdn), subscriber, subscriptionPackDimension, channelDimension,
                null, dateDimension, subscriptionId, DateTime.now().plusDays(5), DateTime.now(), status, null);
        subscription.setLastScheduledMessageDate(new Timestamp(DateTime.now().getMillis()));
        saveAndMarkForDeletion(channelDimension, subscriptionPackDimension, locationDimension, subscriber, subscription);
    }

    @Test
    public void shouldReportChangeMsisdn() throws Exception {
        createSubscriptionForTest();

        mockMvc(subscriptionController)
                .perform(post("/subscription/changemsisdn")
                        .body(TestUtils.toJson(new SubscriberChangeMsisdnReportRequest(subscriptionId, Long.valueOf(msisdn), "reason", DateTime.now())).getBytes())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Subscription subscriptionFromReport = allSubscriptions.findBySubscriptionId(subscriptionId);
        assertEquals(msisdn, subscriptionFromReport.getMsisdn().toString());
    }

    @Test
    public void shouldFetchSubscriberDetailsForAGivenSubscriptionId() throws Exception {
        createSubscriptionForTest();

        SubscriberResponse expectedSubscriberResponse = new SubscriberResponse(subscriptionId, subscriber.getName(),
                subscriber.getAgeOfBeneficiary(), subscriber.getDateOfBirth(), subscriber.getEstimatedDateOfDelivery(),
                new DateTime(subscription.getLastScheduledMessageDate().getTime()), new LocationResponse(locationDimension.getDistrict(), locationDimension.getBlock(), locationDimension.getPanchayat()), new DateTime(subscription.getLastModifiedTime()));

        MvcResult result = mockMvc(subscriptionController)
                .perform(get("/subscription/subscriber/" + subscriptionId))
                .andExpect(status().isOk())
                .andExpect(content().type(APPLICATION_JSON))
                .andReturn();

        String subscriberResponseAsString = result.getResponse().getContentAsString();
        SubscriberResponse actualSubscriberResponse = TestUtils.fromJson(subscriberResponseAsString, SubscriberResponse.class);

        assertEquals(expectedSubscriberResponse, actualSubscriberResponse);
    }

    @Test
    public void shouldCreateCampaignScheduleAlert() throws Exception {
        createSubscriptionForTest();
        String subscriptionId = "subscriptionId";
        String campaignName = "campaign name";
        DateTime scheduledAt = DateTime.now();
        CampaignScheduleAlertRequest scheduleAlertRequest
                = new CampaignScheduleAlertRequest(subscriptionId, campaignName, scheduledAt);
        String scheduleAlertRequestJSON = TestUtils.toJson(scheduleAlertRequest);

        mockMvc(subscriptionController)
                .perform(post("/subscription/campaignScheduleAlert")
                        .body(scheduleAlertRequestJSON.getBytes())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(1, template.loadAll(CampaignScheduleAlertDetails.class).size());
        template.deleteAll(template.loadAll(CampaignScheduleAlertDetails.class));
    }
}
