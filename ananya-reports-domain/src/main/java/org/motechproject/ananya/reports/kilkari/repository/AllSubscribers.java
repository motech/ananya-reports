package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
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
