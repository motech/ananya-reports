package org.motechproject.ananya.kilkari.reports.web.mapper;

import org.motechproject.ananya.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.kilkari.contract.response.SubscriptionResponse;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;

public class SubscriptionMapper {
    public static SubscriptionResponse mapFrom(Subscription subscription) {
        Subscriber subscriber = subscription.getSubscriber();
        LocationDimension locationDimension = subscriber.getLocationDimension();
        return new SubscriptionResponse(subscription.getSubscriptionId(),
                subscription.getSubscriptionPackDimension().getSubscriptionPack(),
                subscriber.getName(),
                subscription.getSubscriptionStatus(),
                String.valueOf(subscription.getWeekNumber()),
                subscriber.getAgeOfBeneficiary() == null ? null : String.valueOf(subscriber.getAgeOfBeneficiary()),
                subscriber.getEstimatedDateOfDelivery() == null ? null : subscriber.getEstimatedDateOfDelivery().toString("dd-MM-yyyy"),
                subscriber.getDateOfBirth() == null ? null : subscriber.getDateOfBirth().toString("dd-MM-yyyy"),
                locationDimension == null ? null : new LocationResponse(locationDimension.getDistrict(), locationDimension.getBlock(), locationDimension.getPanchayat())
        );
    }
}
