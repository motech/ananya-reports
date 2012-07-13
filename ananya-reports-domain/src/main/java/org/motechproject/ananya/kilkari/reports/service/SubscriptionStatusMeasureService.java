package org.motechproject.ananya.kilkari.reports.service;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.WeekNumber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionStatusMeasureService {

    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasure;
    private AllChannelDimensions allChannelDimensions;
    private AllOperatorDimensions allOperatorDimensions;
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    private AllDateDimensions allTimeDimension;
    private AllSubscriptions allSubscriptions;
    private SubscriptionService subscriptionService;
    private AllSubscribers allSubscribers;
    private AllLocationDimensions allLocationDimensions;

    public SubscriptionStatusMeasureService() {
    }

    @Autowired
    public SubscriptionStatusMeasureService(AllSubscriptionStatusMeasure allSubscriptionStatusMeasure,
                                            AllChannelDimensions allChannelDimensions,
                                            AllSubscriptionPackDimensions allSubscriptionPackDimensions,
                                            AllOperatorDimensions allOperatorDimensions, AllSubscribers allSubscribers,
                                            SubscriptionService subscriptionService, AllSubscriptions allSubscriptions,
                                            AllDateDimensions allTimeDimension, AllLocationDimensions allLocationDimensions) {
        this.allSubscriptionStatusMeasure = allSubscriptionStatusMeasure;
        this.allChannelDimensions = allChannelDimensions;
        this.allSubscriptionPackDimensions = allSubscriptionPackDimensions;
        this.allOperatorDimensions = allOperatorDimensions;
        this.allSubscribers = allSubscribers;
        this.subscriptionService = subscriptionService;
        this.allSubscriptions = allSubscriptions;
        this.allTimeDimension = allTimeDimension;
        this.allLocationDimensions = allLocationDimensions;
    }

    @Transactional
    public void createFor(SubscriptionRequest subscriptionRequest) {
        String subscriptionId = subscriptionRequest.getSubscriptionId();
        Long msisdn = subscriptionRequest.getMsisdn();
        if (subscriptionService.exists(subscriptionId)) {
            return;
        }

        ChannelDimension channelDimension = allChannelDimensions.fetchFor(subscriptionRequest.getChannel());
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionRequest.getPack());
        DateDimension dateDimension = allTimeDimension.fetchFor(subscriptionRequest.getCreatedAt());
        SubscriberLocation location = subscriptionRequest.getLocation();
        LocationDimension locationDimension = location == null ? null : allLocationDimensions.fetchFor(location.getDistrict(), location.getBlock(), location.getPanchayat());

        Subscriber subscriber = allSubscribers.save(msisdn, subscriptionRequest.getName(), subscriptionRequest.getAgeOfBeneficiary(), subscriptionRequest.getEstimatedDateOfDelivery(),
                subscriptionRequest.getDateOfBirth(), channelDimension, locationDimension, dateDimension, null);

        Subscription subscription = subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension,
                null, locationDimension, dateDimension, subscriptionId);

        int startingWeekNumber = WeekNumber.getStartingWeekNumberFor(subscriptionRequest.getPack());
        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionRequest.getSubscriptionStatus(),
                startingWeekNumber, null, null, channelDimension, null,
                subscriptionPackDimension, dateDimension);
        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }

    @Transactional
    public void update(SubscriptionStateChangeRequest subscriptionStateChangeRequest) {
        Subscription subscription = subscriptionService.fetchFor(subscriptionStateChangeRequest.getSubscriptionId());
        String subscriptionStatus = subscriptionStateChangeRequest.getSubscriptionStatus();
        DateTime subscriptionRequestedDate = new DateTime(subscription.getDateDimension().getDate());
        String subscriptionPack = subscription.getSubscriptionPackDimension().getSubscriptionPack();

        int subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(subscriptionRequestedDate, subscriptionStateChangeRequest.getCreatedAt(), subscriptionPack);
        DateDimension dateDimension = allTimeDimension.fetchFor(subscriptionStateChangeRequest.getCreatedAt());
        OperatorDimension operatorDimension = StringUtils.isEmpty(subscriptionStateChangeRequest.getOperator()) ?
                subscription.getOperatorDimension() : allOperatorDimensions.fetchFor(subscriptionStateChangeRequest.getOperator());
        subscription.getSubscriber().setOperatorDimension(operatorDimension);
        subscription.setOperatorDimension(operatorDimension);
        allSubscriptions.update(subscription);

        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionStatus,
                subscriptionWeekNumber, subscriptionStateChangeRequest.getReason(), subscriptionStateChangeRequest.getGraceCount(),
                subscription.getChannelDimension(), operatorDimension, subscription.getSubscriptionPackDimension(), dateDimension);

        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }
}
