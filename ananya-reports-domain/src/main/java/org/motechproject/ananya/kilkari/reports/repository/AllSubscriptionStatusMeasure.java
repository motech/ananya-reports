package org.motechproject.ananya.kilkari.reports.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllSubscriptionStatusMeasure {
    @Autowired
    private DataAccessTemplate template;

    public void add(SubscriptionStatusMeasure subscriptionStatusMeasure) {
        template.saveOrUpdate(subscriptionStatusMeasure);
    }

    public List<SubscriptionStatusMeasure> getFor(Long msisdn) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SubscriptionStatusMeasure.class);

        criteria.createAlias("subscription", "sp");
        criteria.createAlias("sp.subscriber", "sb");
        criteria.add(Restrictions.eq("sb.msisdn", msisdn));

        return template.findByCriteria(criteria);
    }
}
