package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SubscriptionService {

    private AllSubscriptions allSubscriptions;

    public SubscriptionService() {
    }

    @Autowired
    public SubscriptionService(AllSubscriptions allSubscriptions) {
        this.allSubscriptions = allSubscriptions;
    }

    public Subscription makeFor(Subscription subscription) {
        return allSubscriptions.save(subscription);
    }

    @Transactional
    public Subscription fetchFor(String subscriptionId) {
        return allSubscriptions.findBySubscriptionId(subscriptionId);
    }

    @Transactional
    public List<Subscription> findByMsisdn(String msisdn) {
        return allSubscriptions.findByMsisdn(tryParse(msisdn));
    }

    @Transactional
    public void deleteFor(Long msisdn) {
        allSubscriptions.deleteFor(msisdn);
    }

    public void updateLastScheduledMessageDate(String subscriptionId, DateTime lastScheduledMessageDate) {
        Subscription subscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        subscription.setLastScheduledMessageDate(new Timestamp(lastScheduledMessageDate.getMillis()));
        allSubscriptions.update(subscription);
    }

    private Long tryParse(String msisdn) {
        try {
            return Long.parseLong(msisdn);
        } catch (Exception e) {
            return null;
        }
    }
}
