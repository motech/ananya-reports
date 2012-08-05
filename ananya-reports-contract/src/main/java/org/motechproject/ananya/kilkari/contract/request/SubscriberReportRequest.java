package org.motechproject.ananya.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriberReportRequest implements Serializable {
    @JsonProperty
    private DateTime createdAt;
    @JsonProperty
    private String beneficiaryName;
    @JsonProperty
    private Integer beneficiaryAge;
    @JsonProperty
    private DateTime expectedDateOfDelivery;
    @JsonProperty
    private DateTime dateOfBirth;
    @JsonProperty
    private SubscriberLocation location;

    public SubscriberReportRequest() {
    }

    public SubscriberReportRequest(DateTime createdAt, String beneficiaryName,
                                   Integer beneficiaryAge, DateTime expectedDateOfDelivery,
                                   DateTime dateOfBirth, SubscriberLocation location) {
        this.createdAt = createdAt;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAge = beneficiaryAge;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
    }

    @JsonIgnore
    public DateTime getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    @JsonIgnore
    public Integer getBeneficiaryAge() {
        return beneficiaryAge;
    }

    @JsonIgnore
    public DateTime getExpectedDateOfDelivery() {
        return expectedDateOfDelivery;
    }

    @JsonIgnore
    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonIgnore
    public SubscriberLocation getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberReportRequest)) return false;

        SubscriberReportRequest that = (SubscriberReportRequest) o;

        return new EqualsBuilder()
                .append(this.beneficiaryAge, that.beneficiaryAge)
                .append(this.beneficiaryName, that.beneficiaryName)
                .append(this.createdAt, that.createdAt)
                .append(this.expectedDateOfDelivery, that.expectedDateOfDelivery)
                .append(this.dateOfBirth, that.dateOfBirth)
                .append(this.location, that.location)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.beneficiaryAge)
                .append(this.beneficiaryName)
                .append(this.createdAt)
                .append(this.expectedDateOfDelivery)
                .append(this.dateOfBirth)
                .append(this.location)
                .hashCode();
    }
}
