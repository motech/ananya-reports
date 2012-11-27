package org.motechproject.ananya.reports.kilkari.contract.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class SubscriptionResponse {

    @JsonProperty
    private Long msisdn;
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String pack;
    @JsonProperty
    private String beneficiaryName;
    @JsonProperty
    private String subscriptionStatus;
    @JsonProperty
    private String weekNumber;
    @JsonProperty
    private String beneficiaryAge;
    @JsonProperty
    private String expectedDateOfDelivery;
    @JsonProperty
    private String dateOfBirth;
    @JsonProperty
    private LocationResponse location;
    @JsonProperty
    private Integer startWeekNumber;

    public SubscriptionResponse() {
    }

    public SubscriptionResponse(Long msisdn, String subscriptionId, String pack, String beneficiaryName, String subscriptionStatus,
                                String weekNumber, String beneficiaryAge, String expectedDateOfDelivery, String dateOfBirth, LocationResponse location, Integer startWeekNumber) {
        this.msisdn = msisdn;
        this.subscriptionId = subscriptionId;
        this.pack = pack;
        this.beneficiaryName = beneficiaryName;
        this.subscriptionStatus = subscriptionStatus;
        this.weekNumber = weekNumber;
        this.beneficiaryAge = beneficiaryAge;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.startWeekNumber = startWeekNumber;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getPack() {
        return pack;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public String getWeekNumber() {
        return weekNumber;
    }

    public String getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public String getExpectedDateOfDelivery() {
        return expectedDateOfDelivery;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public LocationResponse getLocation() {
        return location;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public Integer getStartWeekNumber() {
        return startWeekNumber;
    }
}