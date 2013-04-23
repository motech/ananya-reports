package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class AllSubscribers {
    private DataAccessTemplate template;

    @Autowired
    public AllSubscribers(DataAccessTemplate template) {
        this.template = template;
    }

    public Subscriber save(Subscriber subscriber) {
        template.saveOrUpdate(subscriber);
        return subscriber;
    }

    public List<Subscriber> findByLocation(LocationDimension location) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subscriber.class);
        criteria.createAlias("locationDimension", "location");
        criteria.add(Restrictions.eq("location.district", location.getDistrict()));
        criteria.add(Restrictions.eq("location.block", location.getBlock()));
        criteria.add(Restrictions.eq("location.panchayat", location.getPanchayat()));

        return template.findByCriteria(criteria);
    }

    public void saveOrUpdateAll(List<Subscriber> subscriberList) {
        template.saveOrUpdateAll(subscriberList);
    }

    public void delete(Set<Subscriber> subscribersToDelete) {
        template.deleteAll(subscribersToDelete);
    }
}
