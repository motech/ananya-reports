package org.motechproject.ananya.kilkari.reports.domain.measure;

import org.motechproject.ananya.kilkari.reports.domain.dimension.*;

import javax.persistence.*;

@Entity
@Table(name = "subscriber_call_measure")
public class SubscriberCallMeasure {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Column(name = "call_status")
    private String callStatus;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorDimension operatorDimension;

    @ManyToOne
    @JoinColumn(name = "subscription_pack_id", nullable = false)
    private SubscriptionPackDimension subscriptionPackDimension;

    @Column(name = "service_option")
    private String serviceOption;

    @Column(name = "percentage_listened")
    private Integer percentageListened;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignDimension campaignDimension;

    @ManyToOne
    @JoinColumn(name = "start_date", nullable = false)
    private DateDimension startDate;

    @ManyToOne
    @JoinColumn(name = "start_time", nullable = false)
    private TimeDimension startTime;

    @ManyToOne
    @JoinColumn(name = "end_date", nullable = false)
    private DateDimension endDate;

    @ManyToOne
    @JoinColumn(name = "end_time", nullable = false)
    private TimeDimension endTime;

    public SubscriberCallMeasure() {
    }

    public SubscriberCallMeasure(String callStatus, Integer duration, Integer percentageListened, String serviceOption, Subscription subscription,
                                 OperatorDimension operatorDimension, SubscriptionPackDimension subscriptionPackDimension, CampaignDimension campaignDimension,
                                 DateDimension startDate, TimeDimension startTime, DateDimension endDate, TimeDimension endTime) {
        this.callStatus = callStatus;
        this.duration = duration;
        this.percentageListened = percentageListened;
        this.serviceOption = serviceOption;
        this.subscription = subscription;
        this.operatorDimension = operatorDimension;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.campaignDimension = campaignDimension;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public OperatorDimension getOperatorDimension() {
        return operatorDimension;
    }

    public void setOperatorDimension(OperatorDimension operatorDimension) {
        this.operatorDimension = operatorDimension;
    }

    public SubscriptionPackDimension getSubscriptionPackDimension() {
        return subscriptionPackDimension;
    }

    public void setSubscriptionPackDimension(SubscriptionPackDimension subscriptionPackDimension) {
        this.subscriptionPackDimension = subscriptionPackDimension;
    }

    public String getServiceOption() {
        return serviceOption;
    }

    public CampaignDimension getCampaignDimension() {
        return campaignDimension;
    }

    public DateDimension getStartDate() {
        return startDate;
    }

    public TimeDimension getStartTime() {
        return startTime;
    }

    public DateDimension getEndDate() {
        return endDate;
    }

    public TimeDimension getEndTime() {
        return endTime;
    }

    public Integer getPercentageListened() {
        return percentageListened;
    }
}
