package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.CampaignMessageCallSource;
import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriberCallMeasure;
import org.motechproject.ananya.reports.kilkari.repository.AllCampaignDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriberCallMeasures;
import org.motechproject.ananya.reports.kilkari.repository.AllTimeDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriberCallMeasureService {
    private AllSubscriberCallMeasures allSubscriberCallMeasures;
    private SubscriptionService subscriptionService;
    private AllCampaignDimensions allCampaignDimensions;
    private AllDateDimensions allDateDimensions;
    private AllTimeDimensions allTimeDimensions;
    private OperatorService operatorService;

    public SubscriberCallMeasureService() {
    }

    @Autowired
    public SubscriberCallMeasureService(AllSubscriberCallMeasures allSubscriberCallMeasures, SubscriptionService subscriptionService,
                                        AllCampaignDimensions allCampaignDimensions, AllDateDimensions allDateDimensions, AllTimeDimensions allTimeDimensions, OperatorService operatorService) {
        this.allSubscriberCallMeasures = allSubscriberCallMeasures;
        this.subscriptionService = subscriptionService;
        this.allCampaignDimensions = allCampaignDimensions;
        this.allDateDimensions = allDateDimensions;
        this.allTimeDimensions = allTimeDimensions;
        this.operatorService = operatorService;
    }

    @Transactional
    public void createSubscriberCallDetails(CallDetailsReportRequest callDetailsReportRequest) {
        Subscription subscription = subscriptionService.fetchFor(callDetailsReportRequest.getSubscriptionId());
        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(callDetailsReportRequest.getCampaignId());

        int durationInPulse = operatorService.fetchDurationInPulse(subscription.getOperatorDimension(), callDetailsReportRequest.getDuration());
        allSubscriberCallMeasures.createFor(new SubscriberCallMeasure(
                callDetailsReportRequest.getStatus(),
                callDetailsReportRequest.getDuration(),
                getPercentageListenedTo(callDetailsReportRequest, campaignDimension),
                callDetailsReportRequest.getServiceOption(),
                subscription,
                subscription.getOperatorDimension(),
                subscription.getSubscriptionPackDimension(),
                campaignDimension,
                allDateDimensions.fetchFor(callDetailsReportRequest.getStartTime()),
                allTimeDimensions.fetchFor(callDetailsReportRequest.getStartTime()),
                allDateDimensions.fetchFor(callDetailsReportRequest.getEndTime()),
                allTimeDimensions.fetchFor(callDetailsReportRequest.getEndTime()),
                callDetailsReportRequest.getCallSource(), subscription.getSubscriptionStatus(), durationInPulse));
    }

    @Transactional
    public void deleteFor(Subscription subscription) {
        allSubscriberCallMeasures.deleteFor(subscription);
    }

    private Integer getPercentageListenedTo(CallDetailsReportRequest callDetailsReportRequest, CampaignDimension campaignDimension) {
        Integer messageDuration =
                CampaignMessageCallSource.isOBDCall(callDetailsReportRequest.getCallSource())
                        ? campaignDimension.getObdMessageDuration()
                        : campaignDimension.getInboxMessageDuration();

        Integer percentage = callDetailsReportRequest.getDuration() * 100 / messageDuration;

        return percentage < 0 ? 0 : percentage;
    }
}
