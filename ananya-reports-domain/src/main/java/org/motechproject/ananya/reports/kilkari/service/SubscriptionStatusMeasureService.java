package org.motechproject.ananya.reports.kilkari.service;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionChangePackRequest;
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
    private AllLocationDimensions allLocationDimensions;
    private AllTimeDimensions allTimeDimensions;

    public SubscriptionStatusMeasureService() {
    }

    @Autowired
    public SubscriptionStatusMeasureService(AllSubscriptionStatusMeasure allSubscriptionStatusMeasure,
                                            AllChannelDimensions allChannelDimensions,
                                            AllSubscriptionPackDimensions allSubscriptionPackDimensions,
                                            AllOperatorDimensions allOperatorDimensions, AllSubscribers allSubscribers,
                                            SubscriptionService subscriptionService, AllSubscriptions allSubscriptions,
                                            AllDateDimensions allDateDimensions, AllLocationDimensions allLocationDimensions,
                                            AllTimeDimensions allTimeDimensions) {
        this.allSubscriptionStatusMeasure = allSubscriptionStatusMeasure;
        this.allChannelDimensions = allChannelDimensions;
        this.allSubscriptionPackDimensions = allSubscriptionPackDimensions;
        this.allOperatorDimensions = allOperatorDimensions;
        this.allSubscribers = allSubscribers;
        this.subscriptionService = subscriptionService;
        this.allSubscriptions = allSubscriptions;
        this.allDateDimensions = allDateDimensions;
        this.allLocationDimensions = allLocationDimensions;
        this.allTimeDimensions = allTimeDimensions;
    }

    @Transactional
    public void create(SubscriptionReportRequest subscriptionReportRequest) {
        Long msisdn = subscriptionReportRequest.getMsisdn();
        String subscriptionId = subscriptionReportRequest.getSubscriptionId();
        String subscriptionStatus = subscriptionReportRequest.getSubscriptionStatus();
        if (subscriptionService.exists(subscriptionId)) {
            return;
        }

        ChannelDimension channelDimension = allChannelDimensions.fetchFor(subscriptionReportRequest.getChannel());
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionReportRequest.getPack());
        DateDimension dateDimension = allDateDimensions.fetchFor(subscriptionReportRequest.getCreatedAt());
        TimeDimension timeDimension = allTimeDimensions.fetchFor(subscriptionReportRequest.getCreatedAt());
        SubscriberLocation location = subscriptionReportRequest.getLocation();
        LocationDimension locationDimension = location == null ? null : allLocationDimensions.fetchFor(location.getDistrict(), location.getBlock(), location.getPanchayat());
        OperatorDimension operatorDimension = StringUtils.isEmpty(subscriptionReportRequest.getOperator()) ? null : allOperatorDimensions.fetchFor(subscriptionReportRequest.getOperator());
        Subscription oldSubscription = allSubscriptions.findBySubscriptionId(subscriptionReportRequest.getOldSubscriptionId());

        Subscriber subscriber = saveSubscriber(subscriptionReportRequest, msisdn, channelDimension, dateDimension, locationDimension);
        Subscription subscription = saveSubscription(subscriptionId, channelDimension, subscriptionPackDimension, dateDimension, locationDimension, subscriber, subscriptionReportRequest.getStartDate(), subscriptionReportRequest.getCreatedAt(), subscriptionStatus, null, oldSubscription);
        saveSubscriptionStatusMeasure(subscription, subscriptionStatus, null, dateDimension, timeDimension, operatorDimension, subscriptionReportRequest.getReason(), null);
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
                subscriptionStateChangeRequest.getReason(), subscriptionStateChangeRequest.getGraceCount());
    }

    @Transactional
    public void changePack(SubscriptionChangePackRequest request) {
        String oldSubscriptionId = request.getOldSubscriptionId();
        Subscription oldSubscription = allSubscriptions.findBySubscriptionId(oldSubscriptionId);
        Subscriber oldSubscriber = oldSubscription.getSubscriber();
        LocationDimension oldLocationDimension = oldSubscription.getLocationDimension();

        String name = oldSubscriber.getName();
        Integer age = oldSubscriber.getAgeOfBeneficiary();
        String operator = oldSubscription.getOperatorDimension().getOperator();
        SubscriberLocation location = oldLocationDimension == null ? null : new SubscriberLocation(oldLocationDimension.getDistrict(),
                oldLocationDimension.getBlock(), oldLocationDimension.getPanchayat());

        SubscriptionReportRequest subscriptionReportRequest = new SubscriptionReportRequest(request.getSubscriptionId(), request.getChannel(),
                request.getMsisdn(), request.getPack(), name, age, request.getCreatedAt(), request.getSubscriptionStatus(),
                request.getExpectedDateOfDelivery(), request.getDateOfBirth(), location, operator, request.getStartDate(), oldSubscriptionId, request.getReason());

        create(subscriptionReportRequest);
    }

    private void saveSubscriptionStatusMeasure(Subscription subscription, String subscriptionStatus, Integer subscriptionWeekNumber,
                                               DateDimension dateDimension, TimeDimension timeDimension, OperatorDimension operatorDimension,
                                               String reason, Integer graceCount) {
        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionStatus,
                subscriptionWeekNumber, reason, graceCount, subscription.getChannelDimension(), operatorDimension,
                subscription.getSubscriptionPackDimension(), dateDimension, timeDimension);
        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }

    private Subscription saveSubscription(String subscriptionId, ChannelDimension channelDimension, SubscriptionPackDimension subscriptionPackDimension,
                                          DateDimension dateDimension, LocationDimension locationDimension, Subscriber subscriber,
                                          DateTime startDate, DateTime lastModifiedTime, String subscriptionStatus, Integer weekNumber, Subscription oldSubscription) {
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, null,
                locationDimension, dateDimension, subscriptionId, lastModifiedTime, startDate, subscriptionStatus, weekNumber, oldSubscription);
        subscription = subscriptionService.makeFor(subscription);
        return subscription;
    }

    private Subscriber saveSubscriber(SubscriptionReportRequest subscriptionReportRequest, Long msisdn, ChannelDimension channelDimension, DateDimension dateDimension, LocationDimension locationDimension) {
        Subscriber subscriber = new Subscriber(msisdn, subscriptionReportRequest.getName(), subscriptionReportRequest.getAgeOfBeneficiary(), subscriptionReportRequest.getEstimatedDateOfDelivery(),
                subscriptionReportRequest.getDateOfBirth(), channelDimension, locationDimension, dateDimension, null);
        subscriber = allSubscribers.save(subscriber);
        return subscriber;
    }
}
