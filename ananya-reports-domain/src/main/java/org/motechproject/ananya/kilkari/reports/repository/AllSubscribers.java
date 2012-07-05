package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSubscribers {

    @Autowired
    private DataAccessTemplate template;

    public Subscriber save(String msisdn, String name, int ageOfBeneficiary, DateTime estimatedDateOfDelivery,
                           DateTime dateOfBirth, ChannelDimension channelDimension, LocationDimension locationDimension,
                           TimeDimension timeDimension, OperatorDimension operatorDimension) {

        Subscriber subscriber = new Subscriber(msisdn, name, ageOfBeneficiary, estimatedDateOfDelivery, dateOfBirth,
                channelDimension, locationDimension, timeDimension, operatorDimension);
        template.saveOrUpdate(subscriber);
        return subscriber;
    }
}
