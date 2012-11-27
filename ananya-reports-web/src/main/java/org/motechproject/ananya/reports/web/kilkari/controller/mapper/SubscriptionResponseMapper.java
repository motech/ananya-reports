package org.motechproject.ananya.reports.web.kilkari.controller.mapper;

import org.motechproject.ananya.reports.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriptionResponse;
import org.motechproject.ananya.reports.kilkari.domain.SubscriptionStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;

public class SubscriptionResponseMapper {
    public static SubscriptionResponse mapFrom(Subscription subscription) {
        Subscriber subscriber = subscription.getSubscriber();
        LocationDimension locationDimension = subscriber.getLocationDimension();
        return new SubscriptionResponse(subscription.getMsisdn(), subscription.getSubscriptionId(),
                subscription.getSubscriptionPackDimension().getSubscriptionPack(),
                subscriber.getName(),
                SubscriptionStatus.from(subscription.getSubscriptionStatus()).getDisplayString(),
                String.valueOf(subscription.getCampaignId()),
                subscriber.getAgeOfBeneficiary() == null ? null : String.valueOf(subscriber.getAgeOfBeneficiary()),
                subscriber.getEstimatedDateOfDelivery() == null ? null : subscriber.getEstimatedDateOfDelivery().toString("dd-MM-yyyy"),
                subscriber.getDateOfBirth() == null ? null : subscriber.getDateOfBirth().toString("dd-MM-yyyy"),
                locationDimension == null ? null : new LocationResponse(locationDimension.getDistrict(), locationDimension.getBlock(), locationDimension.getPanchayat()),
                subscriber.getStartWeekNumber());
    }
}
