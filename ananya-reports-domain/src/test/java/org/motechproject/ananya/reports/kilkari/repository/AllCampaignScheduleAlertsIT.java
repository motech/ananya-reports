package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllCampaignScheduleAlertsIT extends SpringIntegrationTest {

    @Autowired
    private AllCampaignScheduleAlerts allCampaignScheduleAlerts;
    @Autowired
    private AllSubscriptions allSubscriptions;
    @Autowired
    private AllDateDimensions allDateDimensions;
    @Autowired
    private AllTimeDimensions allTimeDimensions;

    private ChannelDimension channelDimension;
    private OperatorDimension operatorDimension;
    private LocationDimension locationDimension;
    private DateTime now;
    private DateDimension dateDimension;
    private TimeDimension timeDimension;
    private SubscriptionPackDimension subscriptionPackDimension;
    private Subscription subscription;
    private CampaignDimension campaignDimension;

    @Before
    @After
    public void tearDown() {
        template.deleteAll(template.loadAll(CampaignScheduleAlertDetails.class));
    }

    @Test
    public void shouldSaveScheduleAlertToDb() {
        setUpSubscription();

        allCampaignScheduleAlerts.save(new CampaignScheduleAlertDetails(subscription, campaignDimension, dateDimension, timeDimension));

        assertEquals(1, template.loadAll(CampaignScheduleAlertDetails.class).size());
    }

    @Test
    public void shouldDeleteCampaignScheduleAlertsGivenASubscription(){
        setUpSubscription();

        allCampaignScheduleAlerts.deleteFor(subscription);

        assertEquals(0, template.loadAll(CampaignScheduleAlertDetails.class).size());
    }

    @Test
    public void shouldNotThrowExceptionsIfThereAreNoCMAForAGivenSubscription(){
        allCampaignScheduleAlerts.deleteFor(subscription);

        assertEquals(0, template.loadAll(CampaignScheduleAlertDetails.class).size());
    }

    private void setUpSubscription() {
        channelDimension = new ChannelDimension("IVR");
        template.save(channelDimension);
        markForDeletion(channelDimension);

        operatorDimension = new OperatorDimension("airtel", 0, 60000);
        template.save(operatorDimension);
        markForDeletion(operatorDimension);

        locationDimension = new LocationDimension("state", "district", "block", "panchayat", "VALID");
        template.save(locationDimension);
        markForDeletion(locationDimension);

        now = DateTime.now();
        dateDimension = allDateDimensions.fetchFor(now);
        timeDimension = allTimeDimensions.fetchFor(now);

        subscriptionPackDimension = new SubscriptionPackDimension("NANHI_KILKARI");
        template.save(subscriptionPackDimension);
        markForDeletion(subscriptionPackDimension);

        campaignDimension = new CampaignDimension("WEEK1", 0, 0);
        template.save(campaignDimension);
        markForDeletion(campaignDimension);

        String subscriptionId = "sub11";
        Long msisdn = 123L;
        Subscriber subscriber = new Subscriber("", 0, now, now, channelDimension,
                locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        String subscriptionStatus = "ACTIVE";
        subscription = new Subscription(msisdn, subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, dateDimension, subscriptionId, now, DateTime.now(), subscriptionStatus, null);
        template.save(subscriber);
        template.save(subscription);
        markForDeletion(subscriber);
        markForDeletion(subscription);
    }
}