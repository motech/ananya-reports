package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.CampaignMessageCallSource;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriberCallMeasure;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class AllSubscriberCallMeasuresIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscriberCallMeasures allSubscriberCallMeasures;
    @Autowired
    private AllSubscriptions allSubscriptions;
    @Autowired
    private AllSubscribers allSubscribers;
    @Autowired
    private AllChannelDimensions allChannelDimensions;
    @Autowired
    private AllLocationDimensions allLocationDimensions;
    @Autowired
    private AllTimeDimensions allTimeDimensions;
    @Autowired
    private AllOperatorDimensions allOperatorDimensions;
    @Autowired
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    @Autowired
    private AllDateDimensions allDateDimensions;
    @Autowired
    private AllCampaignDimensions allCampaignDimensions;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(SubscriberCallMeasure.class));
        template.deleteAll(template.loadAll(Subscription.class));
        template.deleteAll(template.loadAll(Subscriber.class));
    }

    @Test
    public void shouldCreateSubscriberMeasure() {
        Integer duration = 61000;
        Integer durationInPulse = 2;
        Integer percentageListened = 90;
        String serviceOption = "HELP";
        String callStatus = "SUCCESS";
        Integer retryCount = 2;
        String campaignId = "week1";
        DateTime subscriberCreatedDateTime = DateTime.now().minusMonths(1);
        DateTime callStartDateTime = DateTime.now();
        Long msisdn = 1234567L;
        DateTime callEndDateTime = callStartDateTime.plusMinutes(10);
        String callSource = CampaignMessageCallSource.OBD.name();
        markForDeletion(template.save(new CampaignDimension(campaignId, 3600, 2400)));

        ChannelDimension channelDimension = allChannelDimensions.fetchFor("IVR");
        OperatorDimension operator = allOperatorDimensions.fetchFor("AIRTEL");
        LocationDimension location = allLocationDimensions.fetchFor("C00", "C00", "");
        DateDimension subscriberCreatedDate = allDateDimensions.fetchFor(subscriberCreatedDateTime);
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor("bari_kilkari");
        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(campaignId);
        DateDimension dateDimension = allDateDimensions.fetchFor(callStartDateTime);
        TimeDimension startTime = allTimeDimensions.fetchFor(callStartDateTime);
        DateDimension endDate = allDateDimensions.fetchFor(callEndDateTime);
        TimeDimension endTime = allTimeDimensions.fetchFor(callEndDateTime);
        Subscriber subscriber = new Subscriber(null, 25, null, null, channelDimension, location, subscriberCreatedDate, operator, null);
        subscriber = allSubscribers.save(subscriber);
        Subscription subscription = new Subscription(msisdn, subscriber, subscriptionPackDimension, channelDimension, operator,
                dateDimension, "123", DateTime.now(), subscriberCreatedDateTime, "NEW", null);
        subscription = allSubscriptions.save(subscription);

        allSubscriberCallMeasures.createFor(new SubscriberCallMeasure(
                callStatus,
                duration,
                percentageListened,
                serviceOption,
                subscription,
                operator,
                subscriptionPackDimension,
                campaignDimension,
                dateDimension,
                startTime,
                endDate,
                endTime,
                retryCount,
                callSource, subscription.getSubscriptionStatus(),durationInPulse));

        List<SubscriberCallMeasure> subscriberCallMeasures = template.loadAll(SubscriberCallMeasure.class);
        assertEquals(1, subscriberCallMeasures.size());
        SubscriberCallMeasure actualSubscriberCallMeasure = subscriberCallMeasures.get(0);
        assertEquals(subscription.getSubscriptionId(), actualSubscriberCallMeasure.getSubscription().getSubscriptionId());
        assertEquals(duration, actualSubscriberCallMeasure.getDuration());
        assertEquals(callStatus, actualSubscriberCallMeasure.getCallStatus());
        assertEquals(retryCount, actualSubscriberCallMeasure.getRetryCount());
        assertEquals(callSource, actualSubscriberCallMeasure.getCallSource());
        assertEquals(durationInPulse,actualSubscriberCallMeasure.getDurationInPulse());
    }

    @Test
    public void shouldRemoveAllMeasuresForAnMsisdn() {
        Integer duration = 50;
        Integer percentageListened = 90;
        String serviceOption = "HELP";
        String callStatus = "SUCCESS";
        Integer retryCount = 2;
        String campaignId = "week1";
        DateTime subscriberCreatedDateTime = DateTime.now().minusMonths(1);
        DateTime callStartDateTime = DateTime.now();
        Long msisdn = 1234567L;
        DateTime callEndDateTime = callStartDateTime.plusMinutes(10);
        String callSource = CampaignMessageCallSource.OBD.name();
        markForDeletion(template.save(new CampaignDimension(campaignId, 3600, 2400)));
        ChannelDimension channelDimension = allChannelDimensions.fetchFor("IVR");
        OperatorDimension operator = allOperatorDimensions.fetchFor("AIRTEL");
        LocationDimension location = allLocationDimensions.fetchFor("C00", "C00", "");
        DateDimension subscriberCreatedDate = allDateDimensions.fetchFor(subscriberCreatedDateTime);
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor("bari_kilkari");
        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(campaignId);
        DateDimension dateDimension = allDateDimensions.fetchFor(callStartDateTime);
        TimeDimension startTime = allTimeDimensions.fetchFor(callStartDateTime);
        DateDimension endDate = allDateDimensions.fetchFor(callEndDateTime);
        TimeDimension endTime = allTimeDimensions.fetchFor(callEndDateTime);
        Subscriber subscriber = new Subscriber(null, 25, null, null, channelDimension, location, subscriberCreatedDate, operator, null);
        subscriber = allSubscribers.save(subscriber);
        Subscription subscription = new Subscription(msisdn, subscriber, subscriptionPackDimension, channelDimension, operator,
                dateDimension, "123", DateTime.now(), subscriberCreatedDateTime, "NEW", null);
        subscription = allSubscriptions.save(subscription);
        allSubscriberCallMeasures.createFor(new SubscriberCallMeasure(
                callStatus,
                duration,
                percentageListened,
                serviceOption,
                subscription,
                operator,
                subscriptionPackDimension,
                campaignDimension,
                dateDimension,
                startTime,
                endDate,
                endTime,
                retryCount,
                callSource, subscription.getSubscriptionStatus(),2));

        allSubscriberCallMeasures.deleteFor(msisdn);

        List<SubscriberCallMeasure> subscriberCallMeasures = template.loadAll(SubscriberCallMeasure.class);
        assertTrue(subscriberCallMeasures.isEmpty());
    }
}
