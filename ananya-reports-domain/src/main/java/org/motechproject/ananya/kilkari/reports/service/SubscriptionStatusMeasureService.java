package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.WeekNumber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SubscriptionStatusMeasureService {

    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasure;
    private AllChannelDimensions allChannelDimensions;
    private AllOperatorDimensions allOperatorDimensions;
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    private AllTimeDimension allTimeDimension;
    private AllSubscriptions allSubscriptions;
    private SubscriptionService subscriptionService;
    private AllSubscribers allSubscribers;

    public SubscriptionStatusMeasureService() {
    }

    @Autowired
    public SubscriptionStatusMeasureService(AllSubscriptionStatusMeasure allSubscriptionStatusMeasure,
                                            AllChannelDimensions allChannelDimensions,
                                            AllSubscriptionPackDimensions allSubscriptionPackDimensions,
                                            AllOperatorDimensions allOperatorDimensions, AllSubscribers allSubscribers,
                                            SubscriptionService subscriptionService, AllSubscriptions allSubscriptions,
                                            AllTimeDimension allTimeDimension) {
        this.allSubscriptionStatusMeasure = allSubscriptionStatusMeasure;
        this.allChannelDimensions = allChannelDimensions;
        this.allSubscriptionPackDimensions = allSubscriptionPackDimensions;
        this.allOperatorDimensions = allOperatorDimensions;
        this.allSubscribers = allSubscribers;
        this.subscriptionService = subscriptionService;
        this.allSubscriptions = allSubscriptions;
        this.allTimeDimension = allTimeDimension;
    }

    @Transactional
    public void createFor(SubscriptionRequest subscriptionRequest) {
        String subscriptionId = subscriptionRequest.getSubscriptionId();
        String msisdn = subscriptionRequest.getMsisdn();
        if (subscriptionService.exists(subscriptionId)) {
            return;
        }

        ChannelDimension channelDimension = allChannelDimensions.fetchFor(subscriptionRequest.getChannel());
        OperatorDimension operatorDimension = allOperatorDimensions.fetchFor(subscriptionRequest.getOperator());
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionRequest.getPack());
        TimeDimension timeDimension = allTimeDimension.fetchFor(subscriptionRequest.getCreatedAt());


        Subscriber subscriber = allSubscribers.save(msisdn, null, 0, null, null, channelDimension, null,
                timeDimension, operatorDimension);

        Subscription subscription = subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, null, timeDimension, subscriptionId);

        int startingWeekNumber = WeekNumber.getStartingWeekNumberFor(subscriptionRequest.getPack());
        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionRequest.getSubscriptionStatus(),
                startingWeekNumber, null, channelDimension, operatorDimension,
                subscriptionPackDimension, timeDimension);
        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }

    @Transactional
    public void update(SubscriptionStateChangeRequest subscriptionStateChangeRequest) {
        Subscription subscription = subscriptionService.fetchFor(subscriptionStateChangeRequest.getSubscriptionId());
        String subscriptionStatus = subscriptionStateChangeRequest.getSubscriptionStatus();
        DateTime subscriptionRequestedDate = new DateTime(subscription.getTimeDimension().getDate());
        String subscriptionPack = subscription.getSubscriptionPackDimension().getSubscriptionPack();

        int subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(subscriptionRequestedDate, subscriptionStateChangeRequest.getCreatedAt(), subscriptionPack);
        TimeDimension timeDimension = allTimeDimension.fetchFor(subscriptionStateChangeRequest.getCreatedAt());
        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(subscription, subscriptionStatus,
                subscriptionWeekNumber, subscriptionStateChangeRequest.getReason(), subscription.getChannelDimension(), subscription.getOperatorDimension(),
                subscription.getSubscriptionPackDimension(), timeDimension);

        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);
    }
}
