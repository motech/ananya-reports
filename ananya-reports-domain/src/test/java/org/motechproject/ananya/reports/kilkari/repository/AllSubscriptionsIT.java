package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AllSubscriptionsIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscriptions allSubscriptions;
    @Autowired
    private AllDateDimensions allDateDimensions;

    private ChannelDimension channelDimension;
    private OperatorDimension operatorDimension;
    private LocationDimension locationDimension;
    private DateTime now;
    private DateDimension dateDimension;
    private SubscriptionPackDimension subscriptionPackDimension;

    @After
    @Before
    public void After() {
        template.deleteAll(template.loadAll(Subscription.class));
        template.deleteAll(template.loadAll(Subscriber.class));
    }

    @Before
    public void setUp() {
        channelDimension = new ChannelDimension("IVR");
        template.save(channelDimension);
        markForDeletion(channelDimension);

        operatorDimension = new OperatorDimension("airtel");
        template.save(operatorDimension);
        markForDeletion(operatorDimension);

        locationDimension = new LocationDimension("district", "block", "panchayat");
        template.save(locationDimension);
        markForDeletion(locationDimension);

        now = DateTime.now();
        dateDimension = allDateDimensions.fetchFor(now);

        subscriptionPackDimension = new SubscriptionPackDimension("PCK1");
        template.save(subscriptionPackDimension);
        markForDeletion(subscriptionPackDimension);
    }

    @Test
    public void shouldFindBySubscriptionId() {
        String subscriptionId = "sub11";
        Subscriber subscriber = new Subscriber(998L, "", 0, now, now, channelDimension,
                locationDimension, dateDimension, operatorDimension);
        int weekNumber = 13;
        String subscriptionStatus = "ACTIVE";
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, dateDimension, subscriptionId, now, DateTime.now(), subscriptionStatus, weekNumber, null);
        template.save(subscriber);
        template.save(subscription);

        Subscription expectedSubscription = allSubscriptions.findBySubscriptionId("sub11");

        assertEquals("sub11", expectedSubscription.getSubscriptionId());
        assertEquals(new Timestamp(now.getMillis()), expectedSubscription.getLastModifiedTime());
        assertEquals(subscriptionStatus, expectedSubscription.getSubscriptionStatus());
        assertEquals(Integer.valueOf(weekNumber), expectedSubscription.getWeekNumber());
    }

    @Test
    public void shouldFindByMsisdn() {
        String subscriptionId1 = "sub11";
        String subscriptionId2 = "sub12";
        Long msisdn = 1234567890L;
        Subscriber subscriber1 = new Subscriber(msisdn, "", 0, now, now, channelDimension, locationDimension, dateDimension, operatorDimension);
        Subscriber subscriber2 = new Subscriber(1234567891L, "", 0, now, now, channelDimension, locationDimension, dateDimension, operatorDimension);
        int weekNumber = 13;
        String subscriptionStatus = "ACTIVE";
        Subscription subscription1 = new Subscription(subscriber1, subscriptionPackDimension, channelDimension, operatorDimension, dateDimension, subscriptionId1, now, now, subscriptionStatus, weekNumber, null);
        Subscription subscription2 = new Subscription(subscriber2, subscriptionPackDimension, channelDimension, operatorDimension, dateDimension, subscriptionId2, now, now, subscriptionStatus, weekNumber, null);
        template.save(subscriber1);
        template.save(subscription1);
        template.save(subscriber2);
        template.save(subscription2);

        List<Subscription> expectedSubscriptions = allSubscriptions.findByMsisdn(msisdn);

        assertEquals(1, expectedSubscriptions.size());
        assertEquals(msisdn, expectedSubscriptions.get(0).getSubscriber().getMsisdn());
    }

    @Test
    public void shouldUpdateAnExistingSubscription() {
        final String subscriptionId = "sub11";
        Subscriber subscriber = new Subscriber(998L, "", 0, now, now, channelDimension,
                locationDimension, dateDimension, operatorDimension);
        template.save(subscriber);

        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, subscriptionId, now, now, "ACTIVE", 13, null);
        allSubscriptions.save(subscription);


        subscription = allSubscriptions.findBySubscriptionId(subscriptionId);

        subscription.setOperatorDimension(operatorDimension);
        allSubscriptions.update(subscription);

        Subscription updatedSubscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        assertEquals("airtel", updatedSubscription.getOperatorDimension().getOperator());
    }
}
