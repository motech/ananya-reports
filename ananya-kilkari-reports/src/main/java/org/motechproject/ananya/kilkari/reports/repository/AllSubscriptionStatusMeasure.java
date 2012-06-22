package org.motechproject.ananya.kilkari.reports.repository;

import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.springframework.beans.factory.annotation.Autowired;

public class AllSubscriptionStatusMeasure {
    @Autowired
    private DataAccessTemplate template;

    public void add(Object subscriptionStatusMeasure) {
        template.saveOrUpdate(subscriptionStatusMeasure);
    }
}
