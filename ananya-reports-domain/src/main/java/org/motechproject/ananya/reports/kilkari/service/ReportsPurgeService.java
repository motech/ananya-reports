package org.motechproject.ananya.reports.kilkari.service;

public class ReportsPurgeService {

    private SubscriberCallMeasureService subscriberCallMeasureService;

    public ReportsPurgeService(SubscriberCallMeasureService subscriberCallMeasureService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
    }

    public void purge(Long msisdn) {
        subscriberCallMeasureService.deleteSubscriberCallDetailsFor(msisdn);
    }
}
