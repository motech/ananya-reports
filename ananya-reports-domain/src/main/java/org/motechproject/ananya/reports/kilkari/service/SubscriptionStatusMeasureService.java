package org.motechproject.ananya.reports.kilkari.service;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.reports.kilkari.domain.WeekNumber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.reports.kilkari.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class SubscriptionStatusMeasureService {

    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasure;
    private AllChannelDimensions allChannelDimensions;
    private AllOperatorDimensions allOperatorDimensions;
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    private AllDateDimensions allDateDimensions;
    private AllSubscriptions allSubscriptions;
    private SubscriptionService subscriptionService;
    private AllSubscribers allSubscribers;
    private LocationService locationService;
    private AllTimeDimensions allTimeDimensions;

    public SubscriptionStatusMeasureService() {
    }

    @Autowired
    public SubscriptionStatusMeasureService(AllSubscriptionStatusMeasure allSubscriptionStatusMeasure,
                                            AllChannelDimensions allChannelDimensions,
                                            AllSubscriptionPackDimensions allSubscriptionPackDimensions,
                                            AllOperatorDimensions allOperatorDimensions, AllSubscribers allSubscribers,
                                            SubscriptionService subscriptionService, AllSubscriptions allSubscriptions,
                                            AllDateDimensions allDateDimensions, LocationService locationService,
                                            AllTimeDimensions allTimeDimensions) {
        this.allSubscriptionStatusMeasure = allSubscriptionStatusMeasure;
        this.allChannelDimensions = allChannelDimensions;
        this.allSubscriptionPackDimensions = allSubscriptionPackDimensions;
        this.allOperatorDimensions = allOperatorDimensions;
        this.allSubscribers = allSubscribers;
        this.subscriptionService = subscriptionService;
        this.allSubscriptions = allSubscriptions;
        this.allDateDimensions = allDateDimensions;
        this.locationService = locationService;
        this.allTimeDimensions = allTimeDimensions;
    }

    @Transactional
    public void createSubscription(SubscriptionReportRequest subscriptionReportRequest) {
        Long msisdn = subscriptionReportRequest.getMsisdn();
        String subscriptionId = subscriptionReportRequest.getSubscriptionId();
        String subscriptionStatus = subscriptionReportRequest.getSubscriptionStatus();

        ChannelDimension channelDimension = allChannelDimensions.fetchFor(subscriptionReportRequest.getChannel());
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionReportRequest.getPack());
        DateTime createdAt = subscriptionReportRequest.getCreatedAt();
        DateDimension dateDimension = allDateDimensions.fetchFor(createdAt);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(createdAt);
        SubscriberLocation location = subscriptionReportRequest.getLocation();
        LocationDimension locationDimension = locationService.createAndFetch(location);
        OperatorDimension operatorDimension = StringUtils.isEmpty(subscriptionReportRequest.getOperator()) ? null : allOperatorDimensions.fetchFor(subscriptionReportRequest.getOperator());
        Subscription oldSubscription = allSubscriptions.findBySubscriptionId(subscriptionReportRequest.getOldSubscriptionId());

        Subscriber subscriber;
        if (isRequestForChangeSubscription(oldSubscription)) {
            operatorDimension = oldSubscription.getOperatorDimension();
            subscriber = fetchExistingSubscriber(subscriptionReportRequest, oldSubscription);
        } else
            subscriber = createNewSubscriber(subscriptionReportRequest, channelDimension, dateDimension, locationDimension);

        subscriber = allSubscribers.save(subscriber);
        Subscription subscription = saveSubscription(msisdn, subscriptionId, channelDimension, operatorDimension, subscriptionPackDimension, dateDimension, subscriber, subscriptionReportRequest.getStartDate(), createdAt, subscriptionStatus, null, oldSubscription);
        saveSubscriptionStatusMeasure(subscription, subscriptionStatus, null, dateDimension, timeDimension, operatorDimension, subscriptionReportRequest.getReason(), null, createdAt);
    }


    @Transactional
    public void update(SubscriptionStateChangeRequest subscriptionStateChangeRequest) {
        Subscription subscription = subscriptionService.fetchFor(subscriptionStateChangeRequest.getSubscriptionId());
        String subscriptionStatus = subscriptionStateChangeRequest.getSubscriptionStatus();
        String subscriptionPack = subscription.getSubscriptionPackDimension().getSubscriptionPack();
        DateTime createdAt = subscriptionStateChangeRequest.getCreatedAt();

        DateTime startDate = new DateTime(subscription.getStartDate());
        Integer subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(startDate, createdAt, subscriptionPack, subscriptionStateChangeRequest.getSubscriptionStatus());
        DateDimension dateDimension = allDateDimensions.fetchFor(createdAt);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(createdAt);
        OperatorDimension operatorDimension = StringUtils.isEmpty(subscriptionStateChangeRequest.getOperator()) ?
                subscription.getOperatorDimension() : allOperatorDimensions.fetchFor(subscriptionStateChangeRequest.getOperator());
        subscription.getSubscriber().setOperatorDimension(operatorDimension);
        subscription.setOperatorDimension(operatorDimension);
        if (new Timestamp(createdAt.getMillis()).compareTo(subscription.getLastModifiedTime()) != -1)
            subscription.updateDetails(createdAt, subscriptionStatus, subscriptionWeekNumber);
        allSubscriptions.update(subscription);

        saveSubscriptionStatusMeasure(subscription, subscriptionStatus, subscriptionWeekNumber, dateDimension, timeDimension, operatorDimension,
                subscriptionStateChangeRequest.getReason(), subscriptionStateChangeRequest.getGraceCount(), createdAt);
    }

    @Transactional
    public void deleteFor(Long msisdn) {
        allSubscriptionStatusMeasure.deleteFor(msisdn);
    }

    private boolean isRequestForChangeSubscription(Subscription oldSubscription) {
        return oldSubscription != null;
    }

    private Subscriber createNewSubscriber(SubscriptionReportRequest subscriptionReportRequest, ChannelDimension channelDimension, DateDimension dateDimension, LocationDimension locationDimension) {
        return new Subscriber(subscriptionReportRequest.getName(), subscriptionReportRequest.getAgeOfBeneficiary(), subscriptionReportRequest.getEstimatedDateOfDelivery(),
                subscriptionReportRequest.getDateOfBirth(), channelDimension, locationDimension, dateDimension, null, subscriptionReportRequest.getStartWeekNumber());
    }

    private Subscriber fetchExistingSubscriber(SubscriptionReportRequest subscriptionReportRequest, Subscription oldSubscription) {
        Subscriber subscriber;
        subscriber = oldSubscription.getSubscriber();
        subscriber.updateSubscriptionDates(subscriptionReportRequest.getEstimatedDateOfDelivery(), subscriptionReportRequest.getDateOfBirth(), subscriptionReportRequest.getStartWeekNumber());
        return subscriber;
    }

    private void saveSubscriptionStatusMeasure(Subscription subscription, String subscriptionStatus, Integer subscriptionWeekNumber,
                                               DateDimension dateDimension, TimeDimension timeDimension, OperatorDimension operatorDimension,
                                               String reason, Integer graceCount, DateTime createdAt) {
        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionStatus,
                subscriptionWeekNumber, reason, graceCount, subscription.getChannelDimension(), operatorDimension,
                subscription.getSubscriptionPackDimension(), dateDimension, timeDimension, createdAt);
        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }

    private Subscription saveSubscription(Long msisdn, String subscriptionId, ChannelDimension channelDimension, OperatorDimension operatorDimension, SubscriptionPackDimension subscriptionPackDimension,
                                          DateDimension dateDimension, Subscriber subscriber,
                                          DateTime startDate, DateTime lastModifiedTime, String subscriptionStatus, Integer campaignId, Subscription oldSubscription) {
        Subscription subscription = new Subscription(msisdn, subscriber, subscriptionPackDimension, channelDimension, operatorDimension,
                dateDimension, subscriptionId, lastModifiedTime, startDate, subscriptionStatus, campaignId, oldSubscription);
        subscription = subscriptionService.makeFor(subscription);
        return subscription;
    }
}
