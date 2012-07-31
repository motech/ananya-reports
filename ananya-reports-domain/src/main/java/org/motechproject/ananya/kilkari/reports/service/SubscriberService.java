package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriberRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
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

    public SubscriberService() {
    }

    @Autowired
    public SubscriberService(AllSubscriptions allSubscriptions, AllSubscribers allSubscribers, AllLocationDimensions allLocationDimensions) {
        this.allSubscriptions = allSubscriptions;
        this.allSubscribers = allSubscribers;
        this.allLocationDimensions = allLocationDimensions;
    }

    public void update(SubscriberRequest subscriberRequest) {
        Subscriber subscriber = allSubscriptions.findBySubscriptionId(subscriberRequest.getSubscriptionId()).getSubscriber();
        SubscriberLocation location = subscriberRequest.getLocation();
        LocationDimension locationDimension = allLocationDimensions.fetchFor(location.getDistrict(), location.getBlock(), location.getPanchayat());
        subscriber.updateWith(subscriberRequest, locationDimension);

        allSubscribers.save(subscriber);
    }
}
