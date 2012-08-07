package org.motechproject.ananya.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriptionReportRequest implements Serializable {

    private String subscriptionId;

    private String channel;

    private Long msisdn;

    private String pack;

    private String name;

    private Integer ageOfBeneficiary;

    private DateTime createdAt;

    private String subscriptionStatus;

    @JsonProperty("edd")
    private DateTime estimatedDateOfDelivery;

    @JsonProperty("dob")
    private DateTime dateOfBirth;

    private SubscriberLocation location;

    private String operator;

    private DateTime startDate;

    public SubscriptionReportRequest() {
    }

    public SubscriptionReportRequest(String subscriptionId, String channel, Long msisdn, String pack, String name, Integer ageOfBeneficiary,
                                     DateTime createdAt, String subscriptionStatus, DateTime estimatedDateOfDelivery, DateTime dateOfBirth,
                                     SubscriberLocation location, String operator, DateTime startDate) {
        this.subscriptionId = subscriptionId;
        this.channel = channel;
        this.msisdn = msisdn;
        this.pack = pack;
        this.name = name;
        this.ageOfBeneficiary = ageOfBeneficiary;
        this.createdAt = createdAt;
        this.subscriptionStatus = subscriptionStatus;
        this.estimatedDateOfDelivery = estimatedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.operator = operator;
        this.startDate = startDate;
    }

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

    public Long getMsisdn() {
        return msisdn;
    }

    public String getPack() {
        return pack;
    }

    public String getName() {
        return name;
    }

    public Integer getAgeOfBeneficiary() {
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

    public void setAgeOfBeneficiary(Integer ageOfBeneficiary) {
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

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }
}
