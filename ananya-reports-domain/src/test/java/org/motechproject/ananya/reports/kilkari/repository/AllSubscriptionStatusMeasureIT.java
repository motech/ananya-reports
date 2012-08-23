package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AllSubscriptionStatusMeasureIT extends SpringIntegrationTest {
    @Autowired
    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasure;
    @Autowired
    private AllDateDimensions allDateDimensions;
    @Autowired
    private AllTimeDimensions allTimeDimensions;
    @Autowired
    private AllChannelDimensions allChannelDimensions;
    @Autowired
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;


    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(SubscriptionStatusMeasure.class));
    }

    @Test
    public void shouldAddNewSubscriptionStatusMeasure() {
        String subscriptionPack = "CHOTI_KILKARI";
        String subscriptionId = "subscriptionId";
        DateTime createdAt = DateTime.now();

        ChannelDimension channelDimension = allChannelDimensions.fetchFor("IVR");
        DateDimension dateDimension = allDateDimensions.fetchFor(createdAt);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(createdAt);
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionPack);

        Subscriber subscriber = new Subscriber("name", 12, DateTime.now(), DateTime.now().minusYears(23), channelDimension, null, dateDimension, null);
        template.save(subscriber);
        Subscriber subscriberFromDb = template.loadAll(Subscriber.class).get(0);

        Subscription subscription = new Subscription(1234L, subscriberFromDb, subscriptionPackDimension, channelDimension, null, dateDimension, subscriptionId, DateTime.now(), DateTime.now(), "ACTIVE", 13, null);
        template.save(subscription);
        Subscription subscriptionFromDb = template.loadAll(Subscription.class).get(0);

        allSubscriptionStatusMeasure.add(new SubscriptionStatusMeasure(subscriptionFromDb, "ACTIVE", 13, null, null, channelDimension, null, subscriptionPackDimension, dateDimension, timeDimension));

        List<SubscriptionStatusMeasure> subscriptionStatusMeasures = template.loadAll(SubscriptionStatusMeasure.class);
        assertEquals(1, subscriptionStatusMeasures.size());
        assertEquals(subscriptionFromDb, subscriptionStatusMeasures.get(0).getSubscription());
    }
}
