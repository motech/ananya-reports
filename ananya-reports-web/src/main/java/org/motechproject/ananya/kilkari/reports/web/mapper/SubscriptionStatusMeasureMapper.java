package org.motechproject.ananya.kilkari.reports.web.mapper;

import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.web.response.LocationResponse;
import org.motechproject.ananya.kilkari.reports.web.response.SubscriptionResponse;

public class SubscriptionStatusMeasureMapper {
    public static SubscriptionResponse mapFrom(SubscriptionStatusMeasure subscriptionStatusMeasure) {
        Subscriber subscriber = subscriptionStatusMeasure.getSubscription().getSubscriber();
        return new SubscriptionResponse(subscriptionStatusMeasure.getSubscription().getSubscriptionId(),
                subscriptionStatusMeasure.getSubscriptionPackDimension().getSubscriptionPack(),
                subscriber.getName(),
                subscriptionStatusMeasure.getStatus(),
                String.valueOf(subscriptionStatusMeasure.getWeekNumber()),
                String.valueOf(subscriber.getAgeOfBeneficiary()),
                subscriber.getEstimatedDateOfDelivery().toString(),
                subscriber.getDateOfBirth().toString(),
                new LocationResponse(subscriber.getLocationDimension())
        );
    }
}
