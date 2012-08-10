package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriberCallMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSubscriberCallMeasures {
    @Autowired
    private DataAccessTemplate template;

    public void createFor(SubscriberCallMeasure subscriberCallMeasure) {
        template.save(subscriberCallMeasure);
    }
}
