package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void createFor(SubscriptionRequest subscriptionRequest) {
        String subscriptionId = subscriptionRequest.getSubscriptionId();
        if(subscriptionService.exists(subscriptionId))
            return;

        ChannelDimension channelDimension = allChannelDimensions.fetchFor(subscriptionRequest.getChannel());
        OperatorDimension operatorDimension = allOperatorDimensions.fetchFor(subscriptionRequest.getOperator());
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionRequest.getPack());
        TimeDimension timeDimension = allTimeDimension.makeFor(DateTime.now());

        String msisdn = subscriptionRequest.getMsisdn();
        Subscriber subscriber = allSubscribers.getOrMakeFor(msisdn, null, 0, null, null, channelDimension, null,
                timeDimension, operatorDimension);

        Subscription subscription = subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension, operatorDimension, null,
                timeDimension, subscriptionId);

        SubscriptionStatusMeasure subscriptionStatusMeasure = new SubscriptionStatusMeasure(
                subscription, "PENDING", subscriptionRequest.getSubscriptionWeekNumber(),
                channelDimension, operatorDimension, subscriptionPackDimension, timeDimension );
        allSubscriptionStatusMeasure.add(subscriptionStatusMeasure);

    }
}
