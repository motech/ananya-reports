package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionRequestService {

    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;

    public SubscriptionRequestService(SubscriptionStatusMeasureService subscriptionStatusMeasureService) {
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
    }

    public void createSubscription(SubscriptionRequest subscriptionRequest) {
        subscriptionStatusMeasureService.createFor(subscriptionRequest);
    }
}
