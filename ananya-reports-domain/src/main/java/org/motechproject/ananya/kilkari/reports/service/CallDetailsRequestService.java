package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.internal.CallDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallDetailsRequestService {
    private SubscriberCallMeasureService subscriberCallMeasureService;

    @Autowired
    public CallDetailsRequestService(SubscriberCallMeasureService subscriberCallMeasureService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
    }

    public void createSubscriberCallDetails(CallDetailsRequest callDetailsRequest) {
        subscriberCallMeasureService.createSubscriberCallDetails(callDetailsRequest);
    }
}
