package org.motechproject.ananya.reports.kilkari.purge.service;

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
        List<String> msisdnList = readFile(filePath);

        Set<Subscription> allSubscriptionsToPurge = findAllSubscriptionsToPurge(msisdnList);

        for (Subscription subscription : allSubscriptionsToPurge) {
            purgeMeasuresFor(subscription);
        }

        purgeDimensions(allSubscriptionsToPurge);
    }

    private List<String> readFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        return FileUtils.readLines(inputFile);
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
}
