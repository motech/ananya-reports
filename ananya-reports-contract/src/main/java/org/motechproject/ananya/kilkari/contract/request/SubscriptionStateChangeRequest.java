package org.motechproject.ananya.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class SubscriptionStateChangeRequest {
    @JsonProperty
    private String subscriptionId;

    @JsonProperty
    private String subscriptionStatus;

    @JsonProperty
    private String reason;

    @JsonProperty
    private DateTime createdAt;

    @JsonProperty
    private String operator;

    @JsonProperty
    private Integer graceCount;

    public SubscriptionStateChangeRequest() {
    }

    public SubscriptionStateChangeRequest(String subscriptionId, String subscriptionStatus, String reason, DateTime createdAt, String operator, Integer graceCount) {
        this.subscriptionId = subscriptionId;
        this.subscriptionStatus = subscriptionStatus;
        this.reason = reason;
        this.createdAt = createdAt;
        this.operator = operator;
        this.graceCount = graceCount;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public String getReason() {
        return reason;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public String getOperator() {
        return operator;
    }

    public Integer getGraceCount() {
        return graceCount;
    }
}