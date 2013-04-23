package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscribers;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class SubscriberService {

    private AllSubscriptions allSubscriptions;
    private AllSubscribers allSubscribers;
    private LocationService locationService;
    private AllDateDimensions allDateDimensions;

    public SubscriberService() {
    }

    @Autowired
    public SubscriberService(AllSubscriptions allSubscriptions, AllSubscribers allSubscribers, LocationService locationService, AllDateDimensions allDateDimensions) {
        this.allSubscriptions = allSubscriptions;
        this.allSubscribers = allSubscribers;
        this.locationService = locationService;
        this.allDateDimensions = allDateDimensions;
    }

    @Transactional
    public void update(SubscriberReportRequest subscriberReportRequest, String subscriptionId) {
        Subscriber subscriber = allSubscriptions.findBySubscriptionId(subscriptionId).getSubscriber();
        LocationDimension locationDimension = locationService.createAndFetch(subscriberReportRequest.getLocation());
        DateDimension dateDimension = allDateDimensions.fetchFor(subscriberReportRequest.getCreatedAt());

        subscriber.updateWith(subscriberReportRequest, locationDimension, dateDimension);

        allSubscribers.save(subscriber);
    }

    public void deleteFor(Set<Subscription> subscriptions) {
        Set<Subscriber> subscribersToDelete = new HashSet<>();
        for (Subscription subscription : subscriptions) {
            subscribersToDelete.add(subscription.getSubscriber());
        }
        allSubscribers.delete(subscribersToDelete);
    }
}
