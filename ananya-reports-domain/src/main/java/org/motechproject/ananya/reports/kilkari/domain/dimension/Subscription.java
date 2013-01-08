package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions", uniqueConstraints = {@UniqueConstraint(columnNames = {"subscription_id"})})
public class Subscription {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "msisdn")
    private long msisdn;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    @Cascade(value = {CascadeType.DELETE})
    private Subscriber subscriber;

    @OneToOne
    @JoinColumn(name = "old_subscription_id")
    @Cascade(value = {CascadeType.DELETE})
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

    @Column(name = "last_scheduled_message_date")
    private Timestamp lastScheduledMessageDate;

    public Subscription() {
    }

    public Subscription(Long msisdn, Subscriber subscriber, SubscriptionPackDimension subscriptionPackDimension,
                        ChannelDimension channelDimension, OperatorDimension operatorDimension,
                        DateDimension dateDimension, String subscriptionId,
                        DateTime lastModifiedTime, DateTime startDate, String subscriptionStatus, Subscription oldSubscription) {
        this.msisdn = msisdn;
        this.subscriber = subscriber;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.channelDimension = channelDimension;
        this.operatorDimension = operatorDimension;
        this.dateDimension = dateDimension;
        this.subscriptionId = subscriptionId;
        this.oldSubscription = oldSubscription;
        this.startDate = new Timestamp(startDate.getMillis());
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.subscriptionStatus = subscriptionStatus;
    }

    public Integer getId() {
        return id;
    }

    public Long getMsisdn() {
        return msisdn;
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

    public void setOperatorDimension(OperatorDimension operatorDimension) {
        this.operatorDimension = operatorDimension;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getLastScheduledMessageDate() {
        return lastScheduledMessageDate;
    }

    public void updateDetails(DateTime lastModifiedTime, String subscriptionStatus) {
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.subscriptionStatus = subscriptionStatus;
    }

    public Subscription getOldSubscription() {
        return oldSubscription;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public void setLastScheduledMessageDate(Timestamp lastScheduledMessageDate) {
        this.lastScheduledMessageDate = lastScheduledMessageDate;
    }
}
