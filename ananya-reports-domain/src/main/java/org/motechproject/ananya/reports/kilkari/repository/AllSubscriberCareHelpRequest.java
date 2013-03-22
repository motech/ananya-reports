package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.SubscriberCareRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
