package org.motechproject.ananya.kilkari.reports.domain.dimension;

import javax.persistence.*;

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

    public Subscription() {
    }

    public Subscription(Subscriber subscriber, SubscriptionPackDimension subscriptionPackDimension,
                        ChannelDimension channelDimension, OperatorDimension operatorDimension,
                        LocationDimension locationDimension, DateDimension dateDimension, String subscriptionId) {
        this.subscriber = subscriber;
        this.subscriptionPackDimension = subscriptionPackDimension;
        this.channelDimension = channelDimension;
        this.operatorDimension = operatorDimension;
        this.locationDimension = locationDimension;
        this.dateDimension = dateDimension;
        this.subscriptionId = subscriptionId;
        this.locationDimension = locationDimension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionPackDimension getSubscriptionPackDimension() {
        return subscriptionPackDimension;
    }

    public void setSubscriptionPackDimension(SubscriptionPackDimension subscriptionPackDimension) {
        this.subscriptionPackDimension = subscriptionPackDimension;
    }

    public ChannelDimension getChannelDimension() {
        return channelDimension;
    }

    public void setChannelDimension(ChannelDimension channelDimension) {
        this.channelDimension = channelDimension;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public OperatorDimension getOperatorDimension() {
        return operatorDimension;
    }

    public void setOperatorDimension(OperatorDimension operatorDimension) {
        this.operatorDimension = operatorDimension;
    }

    public DateDimension getDateDimension() {
        return dateDimension;
    }

    public void setDateDimension(DateDimension dateDimension) {
        this.dateDimension = dateDimension;
    }

    public LocationDimension getLocationDimension() {
        return locationDimension;
    }

    public void setLocationDimension(LocationDimension locationDimension) {
        this.locationDimension = locationDimension;
    }
}
