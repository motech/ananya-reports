package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriberCallMeasure;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;

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
    public void tearDown() {
        template.deleteAll(template.loadAll(SubscriberCallMeasure.class));
        template.deleteAll(template.loadAll(Subscription.class));
        template.deleteAll(template.loadAll(Subscriber.class));
    }

    @Test
    public void shouldCreateSubscriberMeasure() {
        Integer duration = 50;
        Integer percentageListened = 90;
        String serviceOption = "HELP";
        String callStatus = "SUCCESS";
        Integer retryCount = 2;
        String campaignId = "week1";
        DateTime subscriberCreatedDateTime = DateTime.now().minusMonths(1);
        DateTime callStartDateTime = DateTime.now();
        DateTime callEndDateTime = callStartDateTime.plusMinutes(10);
        markForDeletion(template.save(new CampaignDimension(campaignId, 3600)));

        ChannelDimension channelDimension = allChannelDimensions.fetchFor("IVR");
        OperatorDimension operator = allOperatorDimensions.fetchFor("AIRTEL");
        LocationDimension location = allLocationDimensions.fetchFor("C00", "C00", "");
        DateDimension subscriberCreatedDate = allDateDimensions.fetchFor(subscriberCreatedDateTime);
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor("fifteen_months");
        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(campaignId);
        DateDimension dateDimension = allDateDimensions.fetchFor(callStartDateTime);
        TimeDimension startTime = allTimeDimensions.fetchFor(callStartDateTime);
        DateDimension endDate = allDateDimensions.fetchFor(callEndDateTime);
        TimeDimension endTime = allTimeDimensions.fetchFor(callEndDateTime);
        Subscriber subscriber = new Subscriber(99876543210L, null, 25, null, null, channelDimension, location, subscriberCreatedDate, operator);
        subscriber = allSubscribers.save(subscriber);
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, operator,
                location, dateDimension, "123", DateTime.now(), subscriberCreatedDateTime, "NEW", 13);
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
                retryCount));

        List<SubscriberCallMeasure> subscriberCallMeasures = template.loadAll(SubscriberCallMeasure.class);
        assertEquals(1, subscriberCallMeasures.size());
        SubscriberCallMeasure actualSubscriberCallMeasure = subscriberCallMeasures.get(0);
        assertEquals(subscription.getSubscriptionId(), actualSubscriberCallMeasure.getSubscription().getSubscriptionId());
        assertEquals(duration, actualSubscriberCallMeasure.getDuration());
        assertEquals(callStatus, actualSubscriberCallMeasure.getCallStatus());
        assertEquals(retryCount, actualSubscriberCallMeasure.getRetryCount());
    }
}
