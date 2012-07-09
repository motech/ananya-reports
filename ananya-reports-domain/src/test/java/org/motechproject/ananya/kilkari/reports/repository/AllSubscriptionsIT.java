package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@Ignore
public class AllSubscriptionsIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscriptions allSubscriptions;
    @Autowired
    private AllTimeDimension allTimeDimensions;

    @After
    @Before
    public void After() {
        template.deleteAll(template.loadAll(Subscription.class));
        template.deleteAll(template.loadAll(Subscriber.class));
        template.deleteAll(template.loadAll(ChannelDimension.class));
        template.deleteAll(template.loadAll(OperatorDimension.class));
        template.deleteAll(template.loadAll(LocationDimension.class));
        template.deleteAll(template.loadAll(TimeDimension.class));
        template.deleteAll(template.loadAll(SubscriptionPackDimension.class));
    }

    @Test
    public void shouldFindBySubscriptionId(){
        String subscriptionId = "sub11";
        DateTime now = DateTime.now();
        ChannelDimension channelDimension = new ChannelDimension("IVR");
        template.save(channelDimension);
        OperatorDimension operatorDimension = new OperatorDimension("airtel");
        template.save(operatorDimension);
        LocationDimension locationDimension = new LocationDimension("district", "block", "panchayat");
        template.save(locationDimension);
        TimeDimension timeDimension = new TimeDimension(now);
        template.save(timeDimension);
        Subscriber subscriber = new Subscriber(998L,"",0, now, now, channelDimension,
                locationDimension, timeDimension, operatorDimension);
        template.save(subscriber);
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("PCK1");
        template.save(subscriptionPackDimension);

        allSubscriptions.save(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, locationDimension, timeDimension, subscriptionId);

        Subscription subscription = allSubscriptions.findBySubscriptionId("sub11");
        assertEquals("sub11",subscription.getSubscriptionId());
    }

    @Test
    public void shouldUpdateAnExistingSubscription(){
        final String subscriptionId = "sub11";

        final String operator = "airtel";
        final OperatorDimension operatorDimension = new OperatorDimension(operator);


        DateTime now = DateTime.now();
        ChannelDimension channelDimension = new ChannelDimension("IVR");
        template.save(channelDimension);
        template.save(operatorDimension);
        LocationDimension locationDimension = new LocationDimension("district", "block", "panchayat");
        template.save(locationDimension);
        TimeDimension timeDimension = new TimeDimension(now);
        template.save(timeDimension);
        Subscriber subscriber = new Subscriber(998L,"",0, now, now, channelDimension,
                locationDimension, timeDimension, operatorDimension);
        template.save(subscriber);
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("PCK1");
        template.save(subscriptionPackDimension);

        allSubscriptions.save(subscriber, subscriptionPackDimension, channelDimension,
                null, locationDimension, timeDimension, subscriptionId);


        Subscription subscription = allSubscriptions.findBySubscriptionId(subscriptionId);

        subscription.setOperatorDimension(operatorDimension);
        allSubscriptions.update(subscription);

        Subscription updatedSubscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        assertEquals(operator, updatedSubscription.getOperatorDimension().getOperator());
    }
}
