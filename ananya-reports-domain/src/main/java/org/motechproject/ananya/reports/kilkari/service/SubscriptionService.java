package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    public Subscription makeFor(Subscription subscription) {
        return allSubscriptions.save(subscription);
    }

    public Subscription fetchFor(String subscriptionId) {
        return allSubscriptions.findBySubscriptionId(subscriptionId);
    }

    public List<Subscription> findByMsisdn(String msisdn) {
        return allSubscriptions.findByMsisdn(tryParse(msisdn));
    }

    private Long tryParse(String msisdn) {
        try {
            return Long.parseLong(msisdn);
        } catch (Exception e) {
            return null;
        }
    }

    public void changeMsisdnForSubscription(String subscriptionId, Long msisdn) {
        Subscription subscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        subscription.setMsisdn(msisdn);
        allSubscriptions.update(subscription);
    }
}
