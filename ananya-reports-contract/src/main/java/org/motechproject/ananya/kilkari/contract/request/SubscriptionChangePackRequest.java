package org.motechproject.ananya.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriptionChangePackRequest implements Serializable {
    @JsonProperty
    private String msisdn;
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String pack;
    @JsonProperty
    private String channel;
    @JsonProperty
    private String subscriptionStatus;
    @JsonProperty
    private DateTime createdAt;
    @JsonProperty
    private DateTime expectedDateOfDelivery;
    @JsonProperty
    private DateTime dateOfBirth;
    @JsonProperty
    private DateTime startDate;

    public SubscriptionChangePackRequest() {
    }

    public SubscriptionChangePackRequest(String msisdn, String subscriptionId, String pack, String channel, String subscriptionStatus, DateTime createdAt, DateTime expectedDateOfDelivery, DateTime dateOfBirth, DateTime startDate) {
        this.msisdn = msisdn;
        this.subscriptionId = subscriptionId;
        this.pack = pack;
        this.channel = channel;
        this.subscriptionStatus = subscriptionStatus;
        this.createdAt = createdAt;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.startDate = startDate;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getPack() {
        return pack;
    }

    public String getChannel() {
        return channel;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getExpectedDateOfDelivery() {
        return expectedDateOfDelivery;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public DateTime getStartDate() {
        return startDate;
    }
}
