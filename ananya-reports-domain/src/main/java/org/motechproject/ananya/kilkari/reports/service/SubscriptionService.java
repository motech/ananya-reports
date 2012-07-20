package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    private AllSubscriptions allSubscriptions;

    public SubscriptionService() {
    }

    @Autowired
    public SubscriptionService(AllSubscriptions allSubscriptions) {
        this.allSubscriptions = allSubscriptions;
    }

    public boolean exists(String subscriptionId) {
        Subscription bySubscriptionId = allSubscriptions.findBySubscriptionId(subscriptionId);
        return bySubscriptionId != null;
    }

    @Transactional
    public Subscription makeFor(Subscription subscription) {
        return allSubscriptions.save(subscription);
    }

    public Subscription fetchFor(String subscriptionId) {
        return allSubscriptions.findBySubscriptionId(subscriptionId);
    }
}
