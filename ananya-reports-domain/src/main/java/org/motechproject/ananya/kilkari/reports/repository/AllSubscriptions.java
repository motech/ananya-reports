package org.motechproject.ananya.kilkari.reports.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Subscription save(Subscription subscription) {
        template.save(subscription);
        return subscription;
    }

    public void update(Subscription subscription) {
        template.update(subscription);
    }
}
