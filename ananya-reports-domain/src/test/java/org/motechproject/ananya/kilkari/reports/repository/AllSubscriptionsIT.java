package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;

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
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, locationDimension, dateDimension, subscriptionId);
        template.save(subscriber);
        template.save(subscription);

        Subscription expectedSubscription = allSubscriptions.findBySubscriptionId("sub11");

        assertEquals("sub11", expectedSubscription.getSubscriptionId());
    }

    @Test
    public void shouldUpdateAnExistingSubscription() {
        final String subscriptionId = "sub11";
        Subscriber subscriber = new Subscriber(998L, "", 0, now, now, channelDimension,
                locationDimension, dateDimension, operatorDimension);
        template.save(subscriber);

        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, null, locationDimension, dateDimension, subscriptionId);
        allSubscriptions.save(subscription);


        subscription = allSubscriptions.findBySubscriptionId(subscriptionId);

        subscription.setOperatorDimension(operatorDimension);
        allSubscriptions.update(subscription);

        Subscription updatedSubscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        assertEquals("airtel", updatedSubscription.getOperatorDimension().getOperator());
    }
}
