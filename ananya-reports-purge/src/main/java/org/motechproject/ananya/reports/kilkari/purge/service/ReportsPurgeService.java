package org.motechproject.ananya.reports.kilkari.purge.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.io.FileUtils;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReportsPurgeService {
    private SubscriberCallMeasureService subscriberCallMeasureService;
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionService subscriptionService;
    private CampaignScheduleAlertService campaignScheduleAlertService;
    private SubscriberService subscriberService;
    private final Logger logger = LoggerFactory.getLogger(ReportsPurgeService.class);

    @Autowired
    public ReportsPurgeService(SubscriberCallMeasureService subscriberCallMeasureService, SubscriptionStatusMeasureService subscriptionStatusMeasureService, SubscriptionService subscriptionService, CampaignScheduleAlertService campaignScheduleAlertService, SubscriberService subscriberService) {
        this.subscriberCallMeasureService = subscriberCallMeasureService;
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
        this.subscriptionService = subscriptionService;
        this.campaignScheduleAlertService = campaignScheduleAlertService;
        this.subscriberService = subscriberService;
    }

    public void purgeSubscriptionData(String filePath) throws IOException {
        File inputFile = new File(filePath);
        List<String> msisdnList = FileUtils.readLines(inputFile);

        Set<Subscription> allSubscriptionsToPurge = findAllSubscriptionsToPurge(msisdnList);
        for (Subscription subscription : allSubscriptionsToPurge) {
            purgeMeasuresFor(subscription);
        }
        purgeDimensions(allSubscriptionsToPurge);

        writePurgedMsisdnsToFile(allSubscriptionsToPurge, inputFile);
    }

    private Set<Subscription> findAllSubscriptionsToPurge(List<String> msisdnList) {
        Set<Subscription> allRelatedSubscriptions = new LinkedHashSet<>();
        for (String msisdn : msisdnList) {
            msisdn = msisdn.trim();
            if (!msisdn.isEmpty())
                allRelatedSubscriptions.addAll(subscriptionService.getAllRelatedSubscriptions(msisdn));
        }
        return allRelatedSubscriptions;
    }

    private void purgeMeasuresFor(Subscription subscription) {
        logger.info(String.format("Purging report records for msisdn: %s", subscription.getMsisdn()));
        subscriberCallMeasureService.deleteFor(subscription);
        subscriptionStatusMeasureService.deleteFor(subscription);
        campaignScheduleAlertService.deleteFor(subscription);
    }

    private void purgeDimensions(Set<Subscription> allSubscriptionsToPurge) {
        subscriptionService.deleteAll(allSubscriptionsToPurge);
        subscriberService.deleteFor(allSubscriptionsToPurge);
    }

    private void writePurgedMsisdnsToFile(Set<Subscription> allSubscriptionsToPurge, File inputFile) throws IOException {
        Set<String> purgedMsisdns = new LinkedHashSet<>();
        CollectionUtils.collect(allSubscriptionsToPurge, new Transformer() {
            @Override
            public Object transform(Object input) {
                Subscription subscription = (Subscription) input;
                return subscription.getMsisdn();
            }
        }, purgedMsisdns);

        if (purgedMsisdns.isEmpty()) {
            logger.info("No msisdns purged to write it to a file.");
            return;
        }

        String filePath = inputFile.getParent() + "/purged_msisdns";
        logger.info(String.format("Writing purged msisdns to file %s", filePath));
        File outputFile = new File(filePath);
        FileUtils.writeLines(outputFile, purgedMsisdns);
    }
}
