package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.internal.OBDRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OBDRequestService {
    private SubscriberCallMeasureService subscriberCallMeasureService;

    @Autowired
    public OBDRequestService(SubscriberCallMeasureService subscriberCallMeasureService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
    }

    public void createSubscriberCallDetails(OBDRequest obdRequest) {
        subscriberCallMeasureService.createSubscriberCallDetails(obdRequest);
    }
}
