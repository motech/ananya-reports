package org.motechproject.ananya.reports.kilkari.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsPurgeService {
    private SubscriberCallMeasureService subscriberCallMeasureService;
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionService subscriptionService;

    @Autowired
    public ReportsPurgeService(SubscriberCallMeasureService subscriberCallMeasureService, SubscriptionStatusMeasureService subscriptionStatusMeasureService, SubscriptionService subscriptionService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
        this.subscriptionService = subscriptionService;
    }

    public void purge(String msisdn) {
        Long msisdnInLong = Long.valueOf(msisdn);
        subscriberCallMeasureService.deleteFor(msisdnInLong);
        subscriptionStatusMeasureService.deleteFor(msisdnInLong);
        subscriptionService.deleteFor(msisdnInLong);
    }
}
