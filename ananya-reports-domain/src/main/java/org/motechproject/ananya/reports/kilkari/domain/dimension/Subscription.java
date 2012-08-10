package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions", uniqueConstraints = {@UniqueConstraint(columnNames = {"subscription_id"})})
public class Subscription {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private Subscriber subscriber;

    @OneToOne
    @JoinColumn(name = "old_subscription_id")
    private Subscription oldSubscription;

    @ManyToOne
    @JoinColumn(name = "subscription_pack_id", nullable = false)
    private SubscriptionPackDimension subscriptionPackDimension;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private ChannelDimension channelDimension;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorDimension operatorDimension;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationDimension locationDimension;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private DateDimension dateDimension;

    @Column(name = "subscription_id")
    private String subscriptionId;

    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "subscription_status")
    private String subscriptionStatus;

    @Column(name = "week_number")
    private Integer weekNumber;

    public Subscription() {
    }

    public Subscription(Subscriber subscriber, SubscriptionPackDimension subscriptionPackDimension,
                        ChannelDimension channelDimension, OperatorDimension operatorDimension,
                        LocationDimension locationDimension, DateDimension dateDimension, String subscriptionId,
                        DateTime lastModifiedTime, DateTime startDate, String subscriptionStatus, Integer weekNumber, Subscription oldSubscription) {
        this.subscriber = subscriber;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.channelDimension = channelDimension;
        this.operatorDimension = operatorDimension;
        this.locationDimension = locationDimension;
        this.dateDimension = dateDimension;
        this.subscriptionId = subscriptionId;
        this.oldSubscription = oldSubscription;
        this.startDate = new Timestamp(startDate.getMillis());
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.subscriptionStatus = subscriptionStatus;
        this.weekNumber = weekNumber;
    }

    public Integer getId() {
        return id;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public SubscriptionPackDimension getSubscriptionPackDimension() {
        return subscriptionPackDimension;
    }

    public ChannelDimension getChannelDimension() {
        return channelDimension;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public OperatorDimension getOperatorDimension() {
        return operatorDimension;
    }

    public DateDimension getDateDimension() {
        return dateDimension;
    }

    public LocationDimension getLocationDimension() {
        return locationDimension;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setOperatorDimension(OperatorDimension operatorDimension) {
        this.operatorDimension = operatorDimension;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void updateDetails(DateTime lastModifiedTime, String subscriptionStatus, Integer weekNumber) {
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.subscriptionStatus = subscriptionStatus;
        this.weekNumber = weekNumber;
    }

    public Subscription getOldSubscription() {
        return oldSubscription;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
