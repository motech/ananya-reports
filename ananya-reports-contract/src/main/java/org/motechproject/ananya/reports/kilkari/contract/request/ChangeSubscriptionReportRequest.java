package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class ChangeSubscriptionReportRequest implements Serializable {
  
	private static final long serialVersionUID = -8512132191258194375L;

	@JsonProperty
    private String subscriptionId;

    @JsonProperty
    private DateTime startDate;
    
    @JsonProperty
    private DateTime createdAt;

	

	public ChangeSubscriptionReportRequest() {
		super();
	}

	public ChangeSubscriptionReportRequest(String subscriptionId,
			DateTime startDate, DateTime createdAt) {
		super();
		this.subscriptionId = subscriptionId;
		this.startDate = startDate;
		this.createdAt = createdAt;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		 return ToStringBuilder.reflectionToString(this);
	}
}
