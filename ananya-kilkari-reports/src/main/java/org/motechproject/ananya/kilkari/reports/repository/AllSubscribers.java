package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AllSubscribers {
    public Subscriber getOrMakeFor(String msisdn, String name, int ageOfBeneficiary, DateTime estimatedDateOfDelivery,
                                   DateTime dateOfBirth, ChannelDimension channelDimension, LocationDimension locationDimension,
                                   TimeDimension timeDimension, OperatorDimension operatorDimension) {
        return null;

    }
}
