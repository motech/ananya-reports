package org.motechproject.ananya.reports.kilkari.service;

public class ReportsPurgeService {

    private SubscriberCallMeasureService subscriberCallMeasureService;
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;

    public ReportsPurgeService(SubscriberCallMeasureService subscriberCallMeasureService, SubscriptionStatusMeasureService subscriptionStatusMeasureService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
    }

    public void purge(Long msisdn) {
        subscriberCallMeasureService.deleteFor(msisdn);
        subscriptionStatusMeasureService.deleteFor(msisdn);
    }
}
