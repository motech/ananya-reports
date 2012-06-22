package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private AllSubscriptions allSubscriptions;

    @Autowired
    public SubscriptionService(AllSubscriptions allSubscriptions) {
        this.allSubscriptions = allSubscriptions;
    }

    public boolean exists(String subscriptionId) {
        Subscription bySubscriptionId = allSubscriptions.findBySubscriptionId(subscriptionId);
        return bySubscriptionId != null;
    }

    public Subscription makeFor(Subscriber subscriber, SubscriptionPackDimension subscriptionPackDimension,
                        ChannelDimension channelDimension, OperatorDimension operatorDimension,
                        LocationDimension locationDimension, TimeDimension timeDimension, String subscriptionId) {
        return null;

    }
}
