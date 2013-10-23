package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriptionChangeReferredFLWMsisdnReportRequest implements Serializable {

	private static final long serialVersionUID = -876372432587892124L;
	@JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String referredBy;
    @JsonProperty
    private String reason;
    @JsonProperty
    private DateTime createdAt;
    @JsonProperty
    private boolean referredByFlag;

    public SubscriptionChangeReferredFLWMsisdnReportRequest() {
    }

    public SubscriptionChangeReferredFLWMsisdnReportRequest(String subscriptionId, String referredBy, String reason, DateTime createdAt, boolean referredByFlag) {
        this.subscriptionId = subscriptionId;
        this.referredBy = referredBy;
        this.reason = reason;
        this.createdAt = createdAt;
        this.referredByFlag=referredByFlag;
       
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public boolean isReferredByFlag() {
		return referredByFlag;
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
        return "SubscriberChangeReferredFLWMsisdnReportRequest{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", referredBy='" + referredBy +'\'' +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
