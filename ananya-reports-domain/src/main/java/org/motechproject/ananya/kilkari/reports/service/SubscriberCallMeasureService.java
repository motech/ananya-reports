package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.internal.OBDRequest;
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

@Service
public class SubscriberCallMeasureService {
    public static final String DEFAULT_CALL_STATUS = "SUCCESS";

    private AllSubscriberCallMeasures allSubscriberCallMeasures;
    private SubscriptionService subscriptionService;
    private AllCampaignDimensions allCampaignDimensions;
    private AllDateDimensions allDateDimensions;
    private AllTimeDimensions allTimeDimensions;
    private Integer welcomeMessageDuration;

    @Autowired
    public SubscriberCallMeasureService(AllSubscriberCallMeasures allSubscriberCallMeasures, SubscriptionService subscriptionService, AllCampaignDimensions allCampaignDimensions, AllDateDimensions allDateDimensions, AllTimeDimensions allTimeDimensions) {
        this.allSubscriberCallMeasures = allSubscriberCallMeasures;
        this.subscriptionService = subscriptionService;
        this.allCampaignDimensions = allCampaignDimensions;
        this.allDateDimensions = allDateDimensions;
        this.allTimeDimensions = allTimeDimensions;
    }

    public void createSubscriberCallDetails(OBDRequest obdRequest) {
        Subscription subscription = subscriptionService.fetchFor(obdRequest.getSubscriptionId());

        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(obdRequest.getCampaignId());
        allSubscriberCallMeasures.createFor(new SubscriberCallMeasure(
                DEFAULT_CALL_STATUS,
                obdRequest.getDuration(),
                getPercentageListenedTo(obdRequest.getDuration(), campaignDimension.getMessageDuration()),
                obdRequest.getServiceOption(),
                subscription,
                subscription.getOperatorDimension(),
                subscription.getSubscriptionPackDimension(),
                campaignDimension,
                allDateDimensions.fetchFor(obdRequest.getStartTime()),
                allTimeDimensions.fetchFor(obdRequest.getStartTime()),
                allDateDimensions.fetchFor(obdRequest.getEndTime()),
                allTimeDimensions.fetchFor(obdRequest.getEndTime()),
                Integer.parseInt(obdRequest.getRetryCount())));
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
