package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscribers;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void update(SubscriberReportRequest subscriberReportRequest, String subscriptionId) {
        Subscriber subscriber = allSubscriptions.findBySubscriptionId(subscriptionId).getSubscriber();
        SubscriberLocation location = subscriberReportRequest.getLocation();
        LocationDimension locationDimension = allLocationDimensions.fetchFor(location.getDistrict(), location.getBlock(), location.getPanchayat());
        DateDimension dateDimension = allDateDimensions.fetchFor(subscriberReportRequest.getCreatedAt());

        subscriber.updateWith(subscriberReportRequest, locationDimension, dateDimension);

        allSubscribers.save(subscriber);
    }

    public void updateLocation(LocationDimension oldLocation, LocationDimension newLocation) {
        List<Subscriber> subscriberList = allSubscribers.findByLocation(oldLocation);
        for(Subscriber subscriber : subscriberList) {
            subscriber.setLocationDimension(newLocation);
        }
        allSubscribers.saveOrUpdateAll(subscriberList);
    }
}
