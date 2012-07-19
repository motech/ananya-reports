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
    @JoinColumn(name = "date_id", nullable = false)
    private DateDimension dateDimension;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private TimeDimension timeDimension;

    public SubscriptionStatusMeasure() {
    }

    public SubscriptionStatusMeasure(Subscription subscription, String status, int weekNumber, String remarks,
                                     Integer graceCount, ChannelDimension channelDimension, OperatorDimension operatorDimension,
                                     SubscriptionPackDimension subscriptionPackDimension, DateDimension dateDimension, TimeDimension timeDimension) {
        this.subscription = subscription;
        this.status = status;
        this.weekNumber = weekNumber;
        this.remarks = remarks;
        this.graceCount = graceCount;
        this.channelDimension = channelDimension;
        this.operatorDimension = operatorDimension;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.dateDimension = dateDimension;
        this.timeDimension = timeDimension;
    }

    public Integer getId() {
        return id;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public String getStatus() {
        return status;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public ChannelDimension getChannelDimension() {
        return channelDimension;
    }

    public OperatorDimension getOperatorDimension() {
        return operatorDimension;
    }

    public SubscriptionPackDimension getSubscriptionPackDimension() {
        return subscriptionPackDimension;
    }

    public DateDimension getDateDimension() {
        return dateDimension;
    }

    public String getRemarks() {
        return remarks;
    }

    public Integer getGraceCount() {
        return graceCount;
    }

    public TimeDimension getTimeDimension() {
        return timeDimension;
    }

    public boolean isCreatedBefore(SubscriptionStatusMeasure that) {
        int dateDiff = this.getDateDimension().getDate().compareTo(that.getDateDimension().getDate());
        int timeDiff = this.getTimeDimension().compareTo(that.getTimeDimension());
        return dateDiff == 0 ? timeDiff == -1 : dateDiff == -1;
    }
}
