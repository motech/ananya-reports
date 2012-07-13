package org.motechproject.ananya.kilkari.reports.domain.measure;

import org.motechproject.ananya.kilkari.reports.domain.dimension.*;

import javax.persistence.*;

@Entity
@Table(name = "subscription_status_measure")
public class SubscriptionStatusMeasure {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Column(name = "status")
    private String status;

    @Column(name = "week_number")
    private int weekNumber;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "grace_count")
    private Integer graceCount;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private ChannelDimension channelDimension;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorDimension operatorDimension;

    @ManyToOne
    @JoinColumn(name = "subscription_pack_id", nullable = false)
    private SubscriptionPackDimension subscriptionPackDimension;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private DateDimension dateDimension;

    public SubscriptionStatusMeasure() {
    }

    public SubscriptionStatusMeasure(Subscription subscription, String status, int weekNumber, String remarks,
                                     Integer graceCount, ChannelDimension channelDimension, OperatorDimension operatorDimension,
                                     SubscriptionPackDimension subscriptionPackDimension, DateDimension dateDimension) {
        this.subscription = subscription;
        this.status = status;
        this.weekNumber = weekNumber;
        this.remarks = remarks;
        this.graceCount = graceCount;
        this.channelDimension = channelDimension;
        this.operatorDimension = operatorDimension;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.dateDimension = dateDimension;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public ChannelDimension getChannelDimension() {
        return channelDimension;
    }

    public void setChannelDimension(ChannelDimension channelDimension) {
        this.channelDimension = channelDimension;
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

    public DateDimension getDateDimension() {
        return dateDimension;
    }

    public void setDateDimension(DateDimension dateDimension) {
        this.dateDimension = dateDimension;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getGraceCount() {
        return graceCount;
    }

    public void setGraceCount(Integer graceCount) {
        this.graceCount = graceCount;
    }
}
