package org.motechproject.ananya.kilkari.contract.response;

import org.joda.time.DateTime;

public class SubscriberResponse {

    private String beneficiaryName;

    private Integer beneficiaryAge;

    private DateTime dateOfBirth;

    private DateTime expectedDateOfDelivery;

    private LocationResponse locationResponse;


    public SubscriberResponse(String beneficiaryName, Integer beneficiaryAge, DateTime dateOfBirth, DateTime expectedDateOfDelivery, LocationResponse locationResponse) {
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAge = beneficiaryAge;
        this.dateOfBirth = dateOfBirth;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.locationResponse = locationResponse;
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
}
