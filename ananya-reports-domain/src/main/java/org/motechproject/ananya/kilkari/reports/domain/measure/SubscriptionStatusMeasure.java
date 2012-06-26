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

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private ChannelDimension channelDimension;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false)
    private OperatorDimension operatorDimension;

    @ManyToOne
    @JoinColumn(name = "subscription_pack_id", nullable = false)
    private SubscriptionPackDimension subscriptionPackDimension;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private TimeDimension timeDimension;

    public SubscriptionStatusMeasure() {
    }

    public SubscriptionStatusMeasure(Subscription subscription, String status, int weekNumber,
                                     ChannelDimension channelDimension, OperatorDimension operatorDimension,
                                     SubscriptionPackDimension subscriptionPackDimension, TimeDimension timeDimension) {
        this.subscription = subscription;
        this.status = status;
        this.weekNumber = weekNumber;
        this.channelDimension = channelDimension;
        this.operatorDimension = operatorDimension;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.timeDimension = timeDimension;
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

    public TimeDimension getTimeDimension() {
        return timeDimension;
    }

    public void setTimeDimension(TimeDimension timeDimension) {
        this.timeDimension = timeDimension;
    }
}
