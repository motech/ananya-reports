package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriberCallMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllSubscriberCallMeasures {
    private DataAccessTemplate template;

    public AllSubscriberCallMeasures() {
    }

    @Autowired
    public AllSubscriberCallMeasures(DataAccessTemplate template) {
        this.template = template;
    }

    public void createFor(SubscriberCallMeasure subscriberCallMeasure) {
        template.save(subscriberCallMeasure);
    }

    public void deleteFor(Subscription subscription) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SubscriberCallMeasure.class);
        criteria.add(Restrictions.eq("subscription", subscription));
        List<SubscriberCallMeasure> subscriberCallMeasures = template.findByCriteria(criteria);
        template.deleteAll(subscriberCallMeasures);
    }
}
