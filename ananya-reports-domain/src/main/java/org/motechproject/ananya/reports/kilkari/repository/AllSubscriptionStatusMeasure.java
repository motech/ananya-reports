package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
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

    public void deleteFor(Subscription subscription) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SubscriptionStatusMeasure.class);
        criteria.add(Restrictions.eq("subscription", subscription));
        List<SubscriptionStatusMeasure> subscriptionStatusMeasures = template.findByCriteria(criteria);
        template.deleteAll(subscriptionStatusMeasures);
    }
    
    public boolean checkIfEntryIsPresentForSubscriptionStatusAndDate(Subscription subscription, String status, DateDimension dateDimension) {
    	DetachedCriteria criteria = DetachedCriteria.forClass(SubscriptionStatusMeasure.class);
        criteria.add(Restrictions.eq("subscription", subscription));
        criteria.add(Restrictions.eq("dateDimension", dateDimension));
        criteria.add(Property.forName("status").eq(status.toUpperCase()));
        List<SubscriptionStatusMeasure> subscriptionStatusMeasures = template.findByCriteria(criteria);
        return subscriptionStatusMeasures!=null?true:false;
    }
}
