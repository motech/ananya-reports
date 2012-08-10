package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AllSubscriptionStatusMeasure {
    @Autowired
    private DataAccessTemplate template;

    public void add(SubscriptionStatusMeasure subscriptionStatusMeasure) {
        template.saveOrUpdate(subscriptionStatusMeasure);
    }
}
