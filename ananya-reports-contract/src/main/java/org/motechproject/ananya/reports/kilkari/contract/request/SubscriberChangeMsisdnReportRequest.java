package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class SubscriberChangeMsisdnReportRequest {
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private Long msisdn;
    @JsonProperty
    private String reason;
    @JsonProperty
    private DateTime createdAt;

    public SubscriberChangeMsisdnReportRequest() {
    }

    public SubscriberChangeMsisdnReportRequest(String subscriptionId, Long msisdn, String reason, DateTime createdAt) {
        this.subscriptionId = subscriptionId;
        this.msisdn = msisdn;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public SubscriberChangeMsisdnReportRequest(String subscriptionId, Long msisdn, String reason) {
        this.subscriptionId = subscriptionId;
        this.msisdn = msisdn;
        this.reason = reason;
        this.createdAt = DateTime.now();
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public String getReason() {
        return reason;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "SubscriberChangeMsisdnReportRequest{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", msisdn=" + msisdn +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
