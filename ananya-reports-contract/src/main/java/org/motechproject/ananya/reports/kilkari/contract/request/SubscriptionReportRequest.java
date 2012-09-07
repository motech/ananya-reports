package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.ToStringBuilder;
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

    private String oldSubscriptionId;

    private String reason;

    public SubscriptionReportRequest() {
    }

    public SubscriptionReportRequest(String subscriptionId, String channel, Long msisdn, String pack, String name, Integer ageOfBeneficiary,
                                     DateTime createdAt, String subscriptionStatus, DateTime estimatedDateOfDelivery, DateTime dateOfBirth,
                                     SubscriberLocation location, String operator, DateTime startDate, String oldSubscriptionId, String reason) {
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
        this.oldSubscriptionId = oldSubscriptionId;
        this.reason = reason;
    }

    public String getOperator() {
        return operator;
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

    public String getOldSubscriptionId() {
        return oldSubscriptionId;
    }

    public String getReason() {
        return reason;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgeOfBeneficiary(Integer age) {
        this.ageOfBeneficiary = age;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setLocation(SubscriberLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
