package org.motechproject.ananya.kilkari.reports.repository;

import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AllSubscriptions {

    @Autowired
    private DataAccessTemplate template;

    public Subscription findBySubscriptionId(String subscriptionId) {
        return (Subscription) template.getUniqueResult(Subscription.FIND_BY_SUBSCRIPTION_ID,
                new String[]{"subscription_id"}, new Object[]{subscriptionId});
    }

    public Subscription save(Subscriber subscriber, SubscriptionPackDimension subscriptionPackDimension,
                             ChannelDimension channelDimension, OperatorDimension operatorDimension,
                             LocationDimension locationDimension, TimeDimension timeDimension, String subscriptionId) {
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, operatorDimension,
                locationDimension, timeDimension, subscriptionId);
        template.save(subscription);
        return subscription;
    }

    public void update(Subscription subscription) {
        template.update(subscription);
    }
}
