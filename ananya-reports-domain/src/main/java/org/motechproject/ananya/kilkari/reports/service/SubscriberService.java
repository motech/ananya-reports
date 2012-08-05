package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.kilkari.contract.request.SubscriberRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.DateDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.repository.AllDateDimensions;
import org.motechproject.ananya.kilkari.reports.repository.AllLocationDimensions;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscribers;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubscriberService {

    private AllSubscriptions allSubscriptions;
    private AllSubscribers allSubscribers;
    private AllLocationDimensions allLocationDimensions;
    private AllDateDimensions allDateDimensions;

    public SubscriberService() {
    }

    @Autowired
    public SubscriberService(AllSubscriptions allSubscriptions, AllSubscribers allSubscribers, AllLocationDimensions allLocationDimensions, AllDateDimensions allDateDimensions) {
        this.allSubscriptions = allSubscriptions;
        this.allSubscribers = allSubscribers;
        this.allLocationDimensions = allLocationDimensions;
        this.allDateDimensions = allDateDimensions;
    }

    public void update(SubscriberRequest subscriberRequest, String subscriptionId) {
        Subscriber subscriber = allSubscriptions.findBySubscriptionId(subscriptionId).getSubscriber();
        SubscriberLocation location = subscriberRequest.getLocation();
        LocationDimension locationDimension = allLocationDimensions.fetchFor(location.getDistrict(), location.getBlock(), location.getPanchayat());
        DateDimension dateDimension = allDateDimensions.fetchFor(subscriberRequest.getCreatedAt());

        subscriber.updateWith(subscriberRequest, locationDimension, dateDimension);

        allSubscribers.save(subscriber);
    }
}
