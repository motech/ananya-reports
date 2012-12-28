package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriptionStateChangeRequest implements Serializable {
    private static final long serialVersionUID = 8832276651848631156L;
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

    @JsonProperty
    private Integer weekNumber;

    public SubscriptionStateChangeRequest() {
    }

    public SubscriptionStateChangeRequest(String subscriptionId, String subscriptionStatus, String reason, DateTime createdAt, String operator, Integer graceCount, Integer weekNumber) {
        this.subscriptionId = subscriptionId;
        this.subscriptionStatus = subscriptionStatus;
        this.reason = reason;
        this.createdAt = createdAt;
        this.operator = operator;
        this.graceCount = graceCount;
        this.weekNumber = weekNumber;
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

    public Integer getWeekNumber() {
        return weekNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
