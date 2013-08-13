package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AllSubscriptions {

    @Autowired
    private DataAccessTemplate template;

    public Subscription findBySubscriptionId(String subscriptionId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subscription.class);
        criteria.add(Restrictions.eq("subscriptionId", subscriptionId));
        List<Subscription> subscriptions = template.findByCriteria(criteria);

        return subscriptions.isEmpty() ? null : subscriptions.get(0);
    }

    public List<Subscription> findByMsisdn(Long msisdn) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subscription.class);
        criteria.add(Restrictions.eq("msisdn", msisdn));
        return template.findByCriteria(criteria);
    }

    public Subscription save(Subscription subscription) {
        template.save(subscription);
        return subscription;
    }

    public void update(Subscription subscription) {
        template.update(subscription);
    }

    public Set<Subscription> getAllRelatedSubscriptions(List<Subscription> subscriptions) {
        Set<Subscription> allRelatedSubscriptions = new LinkedHashSet<>();
        populateAllReferencedSubscriptions(subscriptions, allRelatedSubscriptions);
        return allRelatedSubscriptions;
    }

    private void populateAllReferencedSubscriptions(List<Subscription> subscriptions, Set<Subscription> allSubscriptions) {
        if (subscriptions.isEmpty())
            return;
        for (Subscription subscription : subscriptions) {
            List<Subscription> newSubscriptions = findByOldSubscription(subscription);
            populateAllReferencedSubscriptions(newSubscriptions, allSubscriptions);
            allSubscriptions.add(subscription);
        }
    }

    private List<Subscription> findByOldSubscription(Subscription subscription) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subscription.class);
        criteria.add(Restrictions.eq("oldSubscription", subscription));
        return template.findByCriteria(criteria);
    }

    public void deleteAll(Set<Subscription> subscriptions) {
        template.deleteAll(subscriptions);
    }
}
