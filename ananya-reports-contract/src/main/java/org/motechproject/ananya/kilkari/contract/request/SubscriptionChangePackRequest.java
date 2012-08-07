package org.motechproject.ananya.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriptionChangePackRequest implements Serializable {
    @JsonProperty
    private Long msisdn;
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String oldSubscriptionId;
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

    public SubscriptionChangePackRequest(Long msisdn, String subscriptionId, String oldSubscriptionId, String pack, String channel, String subscriptionStatus, DateTime createdAt, DateTime expectedDateOfDelivery, DateTime dateOfBirth, DateTime startDate) {
        this.msisdn = msisdn;
        this.subscriptionId = subscriptionId;
        this.oldSubscriptionId = oldSubscriptionId;
        this.pack = pack;
        this.channel = channel;
        this.subscriptionStatus = subscriptionStatus;
        this.createdAt = createdAt;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.startDate = startDate;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getOldSubscriptionId() {
        return oldSubscriptionId;
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

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setOldSubscriptionId(String oldSubscriptionId) {
        this.oldSubscriptionId = oldSubscriptionId;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpectedDateOfDelivery(DateTime expectedDateOfDelivery) {
        this.expectedDateOfDelivery = expectedDateOfDelivery;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }
}
