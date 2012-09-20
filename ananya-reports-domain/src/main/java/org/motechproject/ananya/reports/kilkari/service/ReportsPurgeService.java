package org.motechproject.ananya.reports.kilkari.service;

public class ReportsPurgeService {

    private SubscriberCallMeasureService subscriberCallMeasureService;
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionService subscriptionService;

    public ReportsPurgeService(SubscriberCallMeasureService subscriberCallMeasureService, SubscriptionStatusMeasureService subscriptionStatusMeasureService, SubscriptionService subscriptionService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
        this.subscriptionService = subscriptionService;
    }

    public void purge(Long msisdn) {
        subscriberCallMeasureService.deleteFor(msisdn);
        subscriptionStatusMeasureService.deleteFor(msisdn);
        subscriptionService.deleteFor(msisdn);
    }
}
