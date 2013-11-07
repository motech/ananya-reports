package org.motechproject.ananya.reports.kilkari.contract.request;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;


public class SubscriberChangeSubscriptionReportRequest implements Serializable {
	@JsonProperty
	private DateTime createdAt;
    @JsonProperty
    private DateTime estimatedDateOfDelivery;
    @JsonProperty
    private DateTime dateOfBirth;
    @JsonProperty
    private Integer startWeekNumber;

    public SubscriberChangeSubscriptionReportRequest() {
		super();
	}

	
	   public SubscriberChangeSubscriptionReportRequest(DateTime createdAt,
			DateTime estimatedDateOfDelivery, DateTime dateOfBirth,
			Integer startWeekNumber) {
		super();
		this.createdAt = createdAt;
		this.estimatedDateOfDelivery = estimatedDateOfDelivery;
		this.dateOfBirth = dateOfBirth;
		this.startWeekNumber = startWeekNumber;
	}


	@JsonIgnore
	    public DateTime getCreatedAt() {
	        return createdAt;
	    }
	
	@JsonIgnore
	public DateTime getEstimatedDateOfDelivery() {
		return estimatedDateOfDelivery;
	}

	@JsonIgnore
	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	

	@JsonIgnore
	public Integer getStartWeekNumber() {
		return startWeekNumber;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime
				* result
				+ ((estimatedDateOfDelivery == null) ? 0
						: estimatedDateOfDelivery.hashCode());
		result = prime * result
				+ ((startWeekNumber == null) ? 0 : startWeekNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriberChangeSubscriptionReportRequest other = (SubscriberChangeSubscriptionReportRequest) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (estimatedDateOfDelivery == null) {
			if (other.estimatedDateOfDelivery != null)
				return false;
		} else if (!estimatedDateOfDelivery
				.equals(other.estimatedDateOfDelivery))
			return false;
		if (startWeekNumber == null) {
			if (other.startWeekNumber != null)
				return false;
		} else if (!startWeekNumber.equals(other.startWeekNumber))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SubscriberChangeSubscriptionReportRequest [createdAt="
				+ createdAt + ", estimatedDateOfDelivery="
				+ estimatedDateOfDelivery + ", dateOfBirth=" + dateOfBirth
				+ ", startWeekNumber=" + startWeekNumber + "]";
	}

   
}
