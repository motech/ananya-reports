package org.motechproject.ananya.reports.kilkari.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    public void purgeSubscriptionData(String filePath) throws IOException {
        List<String> msisdnList = readFile(filePath);

        for (String msisdn : msisdnList) {
            purgeFor(msisdn);
        }
    }

    private List<String> readFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        return FileUtils.readLines(inputFile);
    }

    private void purgeFor(String msisdn) {
        msisdn = msisdn.trim();
        if(msisdn.isEmpty()) {
            return;
        }

        logger.info("Started purging report records for msisdn: " + msisdn);
        Long msisdnInLong = Long.valueOf(msisdn);
        subscriberCallMeasureService.deleteFor(msisdnInLong);
        subscriptionStatusMeasureService.deleteFor(msisdnInLong);
        subscriptionService.deleteFor(msisdnInLong);
        logger.info("Finished purging report records for msisdn: " + msisdn);
    }
}
