package org.motechproject.ananya.reports.web.kilkari.controller.mapper;

import org.motechproject.ananya.reports.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;

public class SubscriberMapper {
    public static SubscriberResponse mapFrom(Subscription subscription){
        Subscriber subscriber = subscription.getSubscriber();
        LocationDimension locationDimension = subscriber.getLocationDimension();
        LocationResponse locationResponse = null;
        if (locationDimension != null)
            locationResponse = new LocationResponse(locationDimension.getDistrict(), locationDimension.getBlock(), locationDimension.getPanchayat());

        return new SubscriberResponse(subscriber.getName(), subscriber.getAgeOfBeneficiary(), subscriber.getDateOfBirth(),
                subscriber.getEstimatedDateOfDelivery(), locationResponse);
    }
}
