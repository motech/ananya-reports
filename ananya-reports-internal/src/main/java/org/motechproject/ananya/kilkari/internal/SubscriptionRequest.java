package org.motechproject.ananya.kilkari.internal;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class SubscriptionRequest {

    private String subscriptionId;

    private String channel;

    private Long msisdn;

    private String pack;

    private String name;

    private int ageOfBeneficiary;

    private DateTime createdAt;

    private String subscriptionStatus;

    @JsonProperty("edd")
    private DateTime estimatedDateOfDelivery;

    @JsonProperty("dob")
    private DateTime dateOfBirth;

    private SubscriberLocation location;

    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getChannel() {
        return channel;
    }

    public long getMsisdn() {
        return msisdn;
    }

    public String getPack() {
        return pack;
    }

    public String getName() {
        return name;
    }

    public int getAgeOfBeneficiary() {
        return ageOfBeneficiary;
    }

    public DateTime getEstimatedDateOfDelivery() {
        return estimatedDateOfDelivery;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public SubscriberLocation getLocation() {
        return location;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgeOfBeneficiary(int ageOfBeneficiary) {
        this.ageOfBeneficiary = ageOfBeneficiary;
    }

    public void setEstimatedDateOfDelivery(DateTime estimatedDateOfDelivery) {
        this.estimatedDateOfDelivery = estimatedDateOfDelivery;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLocation(SubscriberLocation location) {
        this.location = location;
    }
}
