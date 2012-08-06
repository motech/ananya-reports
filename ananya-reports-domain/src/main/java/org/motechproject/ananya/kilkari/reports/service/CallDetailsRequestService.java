package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.contract.request.CallDetailsReportRequest;
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
