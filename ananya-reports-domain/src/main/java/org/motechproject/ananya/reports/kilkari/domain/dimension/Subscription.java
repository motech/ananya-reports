package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.domain.MessageCampaignPack;

import javax.persistence.*;
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

    @Column(name = "message_campaign_pack")
    private String messageCampaignPack;

    @Column(name = "referred_by_flw_msisdn")
    private String referredByFLWMsisdn;

    public Subscription() {
    }

    public Subscription(Long msisdn, Subscriber subscriber, SubscriptionPackDimension subscriptionPackDimension,
                        ChannelDimension channelDimension, OperatorDimension operatorDimension,
                        DateDimension dateDimension, String subscriptionId,
                        DateTime lastModifiedTime, DateTime startDate, String subscriptionStatus, Subscription oldSubscription, String referredByFLWMsisdn) {
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
        this.messageCampaignPack = MessageCampaignPack.from(subscriptionPackDimension.getSubscriptionPack()).name();
        this.referredByFLWMsisdn = referredByFLWMsisdn;
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

    public String getReferredByFLWMsisdn() {
		    return referredByFLWMsisdn;
	  }

    public Timestamp getLastScheduledMessageDate() {
        return lastScheduledMessageDate;
    }

    public String getMessageCampaignPack() {
        return messageCampaignPack;
    }

    public void updateStatus(DateTime lastModifiedTime, String subscriptionStatus) {
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.subscriptionStatus = subscriptionStatus;
    }

    public void updateMsisdn(Long msisdn, DateTime lastModifiedTime) {
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.msisdn = msisdn;
    }

    public void updateReferredByFLWMsisdn(String referredByFLWMsisdn, DateTime lastModifiedTime) {
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
        this.referredByFLWMsisdn = referredByFLWMsisdn;
    }

    public void updateMessageCampaignPack(MessageCampaignPack messageCampaignPack, DateTime lastModifiedTime) {
        this.messageCampaignPack = messageCampaignPack.name();
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
    }

    public void setLastScheduledMessageDate(Timestamp lastScheduledMessageDate) {
        this.lastScheduledMessageDate = lastScheduledMessageDate;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
