package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AllSubscriptionStatusMeasure {
    @Autowired
    private DataAccessTemplate template;

    public void add(SubscriptionStatusMeasure subscriptionStatusMeasure) {
        template.saveOrUpdate(subscriptionStatusMeasure);
    }

    public void deleteFor(Long msisdn) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SubscriptionStatusMeasure.class);
        criteria.createAlias("subscription","s");
        criteria.add(Restrictions.eq("s.msisdn", msisdn));
        List<SubscriptionStatusMeasure> subscriptionStatusMeasures = template.findByCriteria(criteria);
        template.deleteAll(subscriptionStatusMeasures);
    }
}
