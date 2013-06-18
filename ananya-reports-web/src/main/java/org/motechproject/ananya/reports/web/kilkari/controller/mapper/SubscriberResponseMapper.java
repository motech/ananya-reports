package org.motechproject.ananya.reports.web.kilkari.controller.mapper;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;

public class SubscriberResponseMapper {
    public static SubscriberResponse mapFrom(Subscription subscription) {
        Subscriber subscriber = subscription.getSubscriber();
        LocationDimension locationDimension = subscriber.getLocationDimension();
        LocationResponse locationResponse = null;
        if (locationDimension != null)
            locationResponse = new LocationResponse(locationDimension.getDistrict(), locationDimension.getBlock(), locationDimension.getPanchayat());
        DateTime lastScheduledMessageDate = subscription.getLastScheduledMessageDate() != null ? new DateTime(subscription.getLastScheduledMessageDate().getTime()) : null;

        return new SubscriberResponse(subscription.getSubscriptionId(), subscriber.getName(), subscriber.getAgeOfBeneficiary(), subscriber.getDateOfBirth(),
                subscriber.getEstimatedDateOfDelivery(), lastScheduledMessageDate, locationResponse, new DateTime(subscription.getLastModifiedTime()), new DateTime(subscriber.getLastModifiedTime()));
    }
}
