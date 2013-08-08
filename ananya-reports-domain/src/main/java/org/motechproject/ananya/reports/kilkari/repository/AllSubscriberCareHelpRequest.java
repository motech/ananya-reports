package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.SubscriberCareRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class AllSubscriberCareHelpRequest {
    private DataAccessTemplate template;

    public AllSubscriberCareHelpRequest() {
    }

    @Autowired
    public AllSubscriberCareHelpRequest(DataAccessTemplate template) {
        this.template = template;
    }

    public void createFor(SubscriberCareRequest subscriberCareRequest) {
        template.save(subscriberCareRequest);
    }

    public List<SubscriberCareRequest> findByMsisdn(String msisdn) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SubscriberCareRequest.class);
        criteria.add(Restrictions.eq("msisdn", msisdn));
        return template.findByCriteria(criteria);
    }

    public void deleteAll(Set<SubscriberCareRequest> subscriberCareRequests) {
        template.deleteAll(subscriberCareRequests);
    }
}
