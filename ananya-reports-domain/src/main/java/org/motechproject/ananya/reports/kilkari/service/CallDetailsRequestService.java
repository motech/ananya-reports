package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallDetailsRequestService {
    private SubscriberCallMeasureService subscriberCallMeasureService;

    @Autowired
    public CallDetailsRequestService(SubscriberCallMeasureService subscriberCallMeasureService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
    }

    public void createSubscriberCallDetails(CallDetailsReportRequest callDetailsReportRequest) {
        subscriberCallMeasureService.createSubscriberCallDetails(callDetailsReportRequest);
    }
}
