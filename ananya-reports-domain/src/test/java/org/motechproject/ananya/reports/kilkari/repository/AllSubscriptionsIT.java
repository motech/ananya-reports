package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.*;

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

        operatorDimension = new OperatorDimension("airtel", 0, 60000);
        template.save(operatorDimension);
        markForDeletion(operatorDimension);

        locationDimension = new LocationDimension("state", "district", "block", "panchayat", "VALID");
        template.save(locationDimension);
        markForDeletion(locationDimension);

        now = DateTime.now();
        dateDimension = allDateDimensions.fetchFor(now);

        subscriptionPackDimension = new SubscriptionPackDimension("NAVJAAT_KILKARI");
        template.save(subscriptionPackDimension);
        markForDeletion(subscriptionPackDimension);
    }

    @Test
    public void shouldFindBySubscriptionId() {
        String subscriptionId = "sub11";
        Subscriber subscriber = new Subscriber("", 0, now, now, channelDimension,
                locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        String subscriptionStatus = "ACTIVE";
        Long msisdn = 123L;
        String referredByFLWMsisdn = "987";
        Subscription subscription = new Subscription(msisdn, subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, dateDimension, subscriptionId, now, DateTime.now(), subscriptionStatus, null, referredByFLWMsisdn, true);
        template.save(subscriber);
        template.save(subscription);

        Subscription expectedSubscription = allSubscriptions.findBySubscriptionId("sub11");

        assertEquals(subscriptionId, expectedSubscription.getSubscriptionId());
        assertEquals(msisdn, expectedSubscription.getMsisdn());
        assertEquals(new Timestamp(now.getMillis()), expectedSubscription.getLastModifiedTime());
        assertEquals(subscriptionStatus, expectedSubscription.getSubscriptionStatus());
    }

    @Test
    public void shouldFindByMsisdn() {
        String subscriptionId1 = "sub11";
        String subscriptionId2 = "sub12";
        Long msisdn = 1234567890L;
        String referredByFLWMsisdn = "9876543210";
        Subscriber subscriber1 = new Subscriber("", 0, now, now, channelDimension, locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        Subscriber subscriber2 = new Subscriber("", 0, now, now, channelDimension, locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        String subscriptionStatus = "ACTIVE";
        Subscription subscription1 = new Subscription(msisdn, subscriber1, subscriptionPackDimension, channelDimension, operatorDimension, dateDimension, subscriptionId1, now, now, subscriptionStatus, null, referredByFLWMsisdn, true);
        Subscription subscription2 = new Subscription(1234L, subscriber2, subscriptionPackDimension, channelDimension, operatorDimension, dateDimension, subscriptionId2, now, now, subscriptionStatus, null, null, false);
        template.save(subscriber1);
        template.save(subscription1);
        template.save(subscriber2);
        template.save(subscription2);

        List<Subscription> expectedSubscriptions = allSubscriptions.findByMsisdn(msisdn);

        assertEquals(1, expectedSubscriptions.size());
        assertEquals(msisdn, expectedSubscriptions.get(0).getMsisdn());
    }

    @Test
    public void shouldUpdateAnExistingSubscription() {
        final String subscriptionId = "sub11";
        Subscriber subscriber = new Subscriber("", 0, now, now, channelDimension,
                locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        template.save(subscriber);

        Subscription subscription = new Subscription(123L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, subscriptionId, now, now, "ACTIVE", null, null, false);
        allSubscriptions.save(subscription);

        subscription = allSubscriptions.findBySubscriptionId(subscriptionId);

        subscription.setOperatorDimension(operatorDimension);
        allSubscriptions.update(subscription);

        Subscription updatedSubscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        assertEquals("airtel", updatedSubscription.getOperatorDimension().getOperator());
    }


    @Test
    public void shouldGetAllTheReferencingSubscriptionsAlso_GivenASubscription() {
        Subscriber subscriber = new Subscriber("", 0, now, now, channelDimension, locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        template.save(subscriber);
        Subscription subscription1 = new Subscription(123L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId1", now, now, "ACTIVE", null, null, false);
        allSubscriptions.save(subscription1);
        Subscription subscription2 = new Subscription(1234L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId2", now, now, "ACTIVE", subscription1, "09876", true);
        allSubscriptions.save(subscription2);
        Subscription subscription3 = new Subscription(123L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId3", now, now, "ACTIVE", subscription2, "54321", true);
        allSubscriptions.save(subscription3);
        Set<Subscription> expectedSubscriptions = new LinkedHashSet<>();
        expectedSubscriptions.add(subscription3);
        expectedSubscriptions.add(subscription2);
        expectedSubscriptions.add(subscription1);

        Set<Subscription> actualRelatedSubscriptions = allSubscriptions.getAllRelatedSubscriptions(Arrays.asList(subscription1));

        assertEquals(expectedSubscriptions, actualRelatedSubscriptions);
    }

    @Test
    public void shouldDeleteGivenSetOfSubscriptions(){
        Subscriber subscriber = new Subscriber("", 0, now, now, channelDimension, locationDimension, dateDimension, operatorDimension, null, DateTime.now());
        template.save(subscriber);
        Subscription subscription1 = new Subscription(123L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId1", now, now, "ACTIVE", null, "987", true);
        allSubscriptions.save(subscription1);
        Subscription subscription2 = new Subscription(1234L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId2", now, now, "ACTIVE", subscription1, null, false);
        allSubscriptions.save(subscription2);
        HashSet<Subscription> subscriptions = new HashSet<>();
        subscriptions.add(subscription1);
        subscriptions.add(subscription2);

        allSubscriptions.deleteAll(subscriptions);

        assertEquals(0, template.loadAll(Subscription.class).size());
    }
}
