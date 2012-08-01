package org.motechproject.ananya.kilkari.reports.web.mapper;

import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.web.response.LocationResponse;
import org.motechproject.ananya.kilkari.reports.web.response.SubscriptionResponse;

public class SubscriptionMapper {
    public static SubscriptionResponse mapFrom(Subscription subscription) {
        Subscriber subscriber = subscription.getSubscriber();
        return new SubscriptionResponse(subscription.getSubscriptionId(),
                subscription.getSubscriptionPackDimension().getSubscriptionPack(),
                subscriber.getName(),
                subscription.getSubscriptionStatus(),
                String.valueOf(subscription.getWeekNumber()),
                subscriber.getAgeOfBeneficiary() == null ? null : String.valueOf(subscriber.getAgeOfBeneficiary()),
                subscriber.getEstimatedDateOfDelivery() == null ? null : subscriber.getEstimatedDateOfDelivery().toString("dd-MM-yyyy"),
                subscriber.getDateOfBirth() == null ? null : subscriber.getDateOfBirth().toString("dd-MM-yyyy"),
                subscriber.getLocationDimension() == null ? null : new LocationResponse(subscriber.getLocationDimension())
        );
    }
}
