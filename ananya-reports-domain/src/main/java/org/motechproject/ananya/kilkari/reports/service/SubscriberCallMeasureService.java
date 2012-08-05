package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.contract.request.CallDetailsRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.CampaignDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriberCallMeasure;
import org.motechproject.ananya.kilkari.reports.repository.AllCampaignDimensions;
import org.motechproject.ananya.kilkari.reports.repository.AllDateDimensions;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriberCallMeasures;
import org.motechproject.ananya.kilkari.reports.repository.AllTimeDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriberCallMeasureService {
    private AllSubscriberCallMeasures allSubscriberCallMeasures;
    private SubscriptionService subscriptionService;
    private AllCampaignDimensions allCampaignDimensions;
    private AllDateDimensions allDateDimensions;
    private AllTimeDimensions allTimeDimensions;
    private Integer welcomeMessageDuration;

    public SubscriberCallMeasureService() {
    }

    @Autowired
    public SubscriberCallMeasureService(AllSubscriberCallMeasures allSubscriberCallMeasures, SubscriptionService subscriptionService, AllCampaignDimensions allCampaignDimensions, AllDateDimensions allDateDimensions, AllTimeDimensions allTimeDimensions) {
        this.allSubscriberCallMeasures = allSubscriberCallMeasures;
        this.subscriptionService = subscriptionService;
        this.allCampaignDimensions = allCampaignDimensions;
        this.allDateDimensions = allDateDimensions;
        this.allTimeDimensions = allTimeDimensions;
    }

    @Transactional
    public void createSubscriberCallDetails(CallDetailsRequest callDetailsRequest) {
        Subscription subscription = subscriptionService.fetchFor(callDetailsRequest.getSubscriptionId());

        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(callDetailsRequest.getCampaignId());
        allSubscriberCallMeasures.createFor(new SubscriberCallMeasure(
                callDetailsRequest.getStatus(),
                callDetailsRequest.getDuration(),
                getPercentageListenedTo(callDetailsRequest.getDuration(), campaignDimension.getMessageDuration()),
                callDetailsRequest.getServiceOption(),
                subscription,
                subscription.getOperatorDimension(),
                subscription.getSubscriptionPackDimension(),
                campaignDimension,
                allDateDimensions.fetchFor(callDetailsRequest.getStartTime()),
                allTimeDimensions.fetchFor(callDetailsRequest.getStartTime()),
                allDateDimensions.fetchFor(callDetailsRequest.getEndTime()),
                allTimeDimensions.fetchFor(callDetailsRequest.getEndTime()),
                Integer.parseInt(callDetailsRequest.getRetryCount()),
                callDetailsRequest.getCallSource()));
    }

    private Integer getPercentageListenedTo(Integer durationListenedTo, Integer messageDuration) {
        Integer percentage = (durationListenedTo - welcomeMessageDuration) * 100 / messageDuration;

        return (percentage < 0) ? 0 : (percentage > 100 ? 100 : percentage);
    }

    @Value("#{campaignProperties['welcome.message.duration']}")
    public void setWelcomeMessageDuration(Integer welcomeMessageDuration) {
        this.welcomeMessageDuration = welcomeMessageDuration;
    }
}
