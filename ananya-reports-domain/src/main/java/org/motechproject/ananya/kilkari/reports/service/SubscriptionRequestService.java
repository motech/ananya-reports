package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionRequestService {

    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;

    public SubscriptionRequestService() {
    }

    public SubscriptionRequestService(SubscriptionStatusMeasureService subscriptionStatusMeasureService) {
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
    }

    public void createSubscription(SubscriptionRequest subscriptionRequest) {
        subscriptionStatusMeasureService.createFor(subscriptionRequest);
    }

    public void updateSubscription(SubscriptionStateChangeRequest subscriptionStateChangeRequest) {
        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);
    }
}
