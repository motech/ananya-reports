package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignChangeReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.MessageCampaignPack;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
public class SubscriptionService {

    private AllSubscriptions allSubscriptions;

    private SubscriberService subscriberService;

    public SubscriptionService() {
    }

    @Autowired
    public SubscriptionService(AllSubscriptions allSubscriptions, SubscriberService subscriberService) {
        this.allSubscriptions = allSubscriptions;
        this.subscriberService = subscriberService;
    }

    public Subscription makeFor(Subscription subscription) {
        return allSubscriptions.save(subscription);
    }

    @Transactional
    public Subscription fetchFor(String subscriptionId) {
        return allSubscriptions.findBySubscriptionId(subscriptionId);
    }

    @Transactional
    public List<Subscription> findByMsisdn(String msisdn) {
        return allSubscriptions.findByMsisdn(tryParse(msisdn));
    }

    @Transactional
    public void deleteCascade(Long msisdn) {
        List<Subscription> subscriptions = findByMsisdn(msisdn.toString());
        Set<Subscription> deletedSubscriptions = allSubscriptions.deleteCascade(subscriptions);
        subscriberService.deleteFor(deletedSubscriptions);
    }

    public void updateLastScheduledMessageDate(String subscriptionId, DateTime lastScheduledMessageDate) {
        Subscription subscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        subscription.setLastScheduledMessageDate(new Timestamp(lastScheduledMessageDate.getMillis()));
        allSubscriptions.update(subscription);
    }

    private Long tryParse(String msisdn) {
        try {
            return Long.parseLong(msisdn);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void updateMessageCampaign(CampaignChangeReportRequest campaignChangeReportRequest, String subscriptionId) {
        Subscription subscription = allSubscriptions.findBySubscriptionId(subscriptionId);
        if (subscription == null)
            return;
        subscription.updateMessageCampaignPack(MessageCampaignPack.from(campaignChangeReportRequest.getMessageCampaignPack()), campaignChangeReportRequest.getCreatedAt());
        allSubscriptions.update(subscription);
    }
}
