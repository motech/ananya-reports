package org.motechproject.ananya.kilkari.internal;

import org.joda.time.DateTime;

public class SubscriberRequest {
    private String subscriptionId;
    private DateTime createdAt;
    private String beneficiaryName;
    private Integer beneficiaryAge;
    private DateTime expectedDateOfDelivery;
    private DateTime dateOfBirth;
    private SubscriberLocation location;

    public SubscriberRequest(String subscriptionId, DateTime createdAt, String beneficiaryName,
                             Integer beneficiaryAge, DateTime expectedDateOfDelivery,
                             DateTime dateOfBirth, SubscriberLocation location) {
        this.subscriptionId = subscriptionId;
        this.createdAt = createdAt;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAge = beneficiaryAge;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public Integer getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public DateTime getExpectedDateOfDelivery() {
        return expectedDateOfDelivery;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public SubscriberLocation getLocation() {
        return location;
    }
}
