package org.motechproject.ananya.kilkari.reports.service;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.WeekNumber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;
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
        int startingWeekNumber = WeekNumber.getStartingWeekNumberFor(subscriptionReportRequest.getPack());
        OperatorDimension operatorDimension = StringUtils.isEmpty(subscriptionReportRequest.getOperator()) ? null : allOperatorDimensions.fetchFor(subscriptionReportRequest.getOperator());

        Subscriber subscriber = saveSubscriber(subscriptionReportRequest, msisdn, channelDimension, dateDimension, locationDimension);
        Subscription subscription = saveSubscription(subscriptionId, channelDimension, subscriptionPackDimension, dateDimension, locationDimension, subscriber, subscriptionReportRequest.getStartDate(), subscriptionReportRequest.getCreatedAt(), subscriptionStatus, startingWeekNumber);
        saveSubscriptionStatusMeasure(subscription, subscriptionStatus, startingWeekNumber, dateDimension, timeDimension, operatorDimension, null, null);
    }

    @Transactional
    public void update(SubscriptionStateChangeRequest subscriptionStateChangeRequest) {
        Subscription subscription = subscriptionService.fetchFor(subscriptionStateChangeRequest.getSubscriptionId());
        String subscriptionStatus = subscriptionStateChangeRequest.getSubscriptionStatus();
        DateTime subscriptionRequestedDate = new DateTime(subscription.getDateDimension().getDate());
        String subscriptionPack = subscription.getSubscriptionPackDimension().getSubscriptionPack();
        DateTime createdAt = subscriptionStateChangeRequest.getCreatedAt();

        int subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(subscriptionRequestedDate, createdAt, subscriptionPack);
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

    private void saveSubscriptionStatusMeasure(Subscription subscription, String subscriptionStatus, int subscriptionWeekNumber,
                                               DateDimension dateDimension, TimeDimension timeDimension, OperatorDimension operatorDimension,
                                               String reason, Integer graceCount) {
        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionStatus,
                subscriptionWeekNumber, reason, graceCount, subscription.getChannelDimension(), operatorDimension,
                subscription.getSubscriptionPackDimension(), dateDimension, timeDimension);
        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }

    private Subscription saveSubscription(String subscriptionId, ChannelDimension channelDimension, SubscriptionPackDimension subscriptionPackDimension,
                                          DateDimension dateDimension, LocationDimension locationDimension, Subscriber subscriber,
                                          DateTime startDate, DateTime lastModifiedTime, String subscriptionStatus, int weekNumber) {
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, null,
                locationDimension, dateDimension, subscriptionId, lastModifiedTime, startDate, subscriptionStatus, weekNumber);
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
