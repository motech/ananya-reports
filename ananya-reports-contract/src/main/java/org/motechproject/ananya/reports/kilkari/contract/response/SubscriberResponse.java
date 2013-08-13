package org.motechproject.ananya.reports.kilkari.contract.response;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

public class SubscriberResponse {

    private String subscriptionId;

    private String beneficiaryName;

    private Integer beneficiaryAge;

    private DateTime dateOfBirth;

    private DateTime expectedDateOfDelivery;

    private DateTime lastScheduledMessageDate;

    private LocationResponse locationResponse;

    private DateTime lastUpdatedTimeForSubscription;

    private DateTime lastUpdatedTimeForBeneficiary;

    public SubscriberResponse() {
    }

    public SubscriberResponse(String subscriptionId, String beneficiaryName, Integer beneficiaryAge, DateTime dateOfBirth, DateTime expectedDateOfDelivery,
                              DateTime lastScheduledMessageDate, LocationResponse locationResponse, DateTime lastUpdatedTimeForSubscription, DateTime lastUpdatedTimeForBeneficiary) {
        this.subscriptionId = subscriptionId;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAge = beneficiaryAge;
        this.dateOfBirth = dateOfBirth;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.lastScheduledMessageDate = lastScheduledMessageDate;
        this.locationResponse = locationResponse;
        this.lastUpdatedTimeForSubscription = lastUpdatedTimeForSubscription;
        this.lastUpdatedTimeForBeneficiary = lastUpdatedTimeForBeneficiary;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public Integer getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public DateTime getExpectedDateOfDelivery() {
        return expectedDateOfDelivery;
    }

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    public DateTime getLastUpdatedTimeForSubscription() {
        return lastUpdatedTimeForSubscription;
    }

    public DateTime getLastUpdatedTimeForBeneficiary() {
        return lastUpdatedTimeForBeneficiary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberResponse)) return false;

        SubscriberResponse that = (SubscriberResponse) o;

        return new EqualsBuilder()
                .append(this.beneficiaryName, that.beneficiaryName)
                .append(this.beneficiaryAge, that.beneficiaryAge)
//                .append(this.dateOfBirth.getMillis(), that.dateOfBirth.getMillis())
//                .append(this.expectedDateOfDelivery.getMillis(), that.expectedDateOfDelivery.getMillis())
                .append(this.locationResponse, that.locationResponse)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.beneficiaryName)
                .append(this.beneficiaryAge)
                .append(this.dateOfBirth)
                .append(this.expectedDateOfDelivery)
                .append(locationResponse)
                .hashCode();
    }

    public DateTime getLastScheduledMessageDate() {
        return lastScheduledMessageDate;
    }
}
