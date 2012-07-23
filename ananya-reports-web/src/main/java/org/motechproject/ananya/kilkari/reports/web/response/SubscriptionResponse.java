package org.motechproject.ananya.kilkari.reports.web.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class SubscriptionResponse {

    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String pack;
    @JsonProperty
    private String beneficiaryName;
    @JsonProperty
    private String subscriptionStatus;
    @JsonProperty
    private String lastCampaignId;
    @JsonProperty
    private String beneficiaryAge;
    @JsonProperty
    private String expectedDateOfDelivery;
    @JsonProperty
    private String dateOfBirth;
    @JsonProperty
    private LocationResponse location;

    public SubscriptionResponse(String subscriptionId, String pack, String beneficiaryName, String subscriptionStatus, String lastCampaignId, String beneficiaryAge, String expectedDateOfDelivery, String dateOfBirth, LocationResponse location) {
        this.subscriptionId = subscriptionId;
        this.pack = pack;
        this.beneficiaryName = beneficiaryName;
        this.subscriptionStatus = subscriptionStatus;
        this.lastCampaignId = lastCampaignId;
        this.beneficiaryAge = beneficiaryAge;
        this.expectedDateOfDelivery = expectedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
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

    public String getLastCampaignId() {
        return lastCampaignId;
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
}