package org.motechproject.ananya.kilkari.reports.repository;

import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSubscribers {

    @Autowired
    private DataAccessTemplate template;

    public Subscriber save(Subscriber subscriber) {
        template.saveOrUpdate(subscriber);
        return subscriber;
    }
}
