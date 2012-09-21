package org.motechproject.ananya.reports.kilkari.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsPurgeService {
    private SubscriberCallMeasureService subscriberCallMeasureService;
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionService subscriptionService;
    private final Logger logger = LoggerFactory.getLogger(ReportsPurgeService.class);

    @Autowired
    public ReportsPurgeService(SubscriberCallMeasureService subscriberCallMeasureService, SubscriptionStatusMeasureService subscriptionStatusMeasureService, SubscriptionService subscriptionService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
        this.subscriptionService = subscriptionService;
    }

    public void purge(String msisdn) {
        logger.info("Started purging report records for msisdn : " + msisdn);
        Long msisdnInLong = Long.valueOf(msisdn);
        subscriberCallMeasureService.deleteFor(msisdnInLong);
        subscriptionStatusMeasureService.deleteFor(msisdnInLong);
        subscriptionService.deleteFor(msisdnInLong);
        logger.info("Finished purging report records for msisdn : " + msisdn);
    }
}
