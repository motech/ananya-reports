package org.motechproject.ananya.kilkari.reports.web.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class SubscriptionResponse {

    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String subscriptionPack;
    @JsonProperty
    private String name;
    @JsonProperty
    private String subscriptionStatus;
    @JsonProperty
    private String campaignId;
    @JsonProperty
    private String ageOfBeneficiary;
    @JsonProperty
    private String estimatedDateOfDelivery;
    @JsonProperty
    private String dateOfBirth;
    @JsonProperty
    private LocationResponse locationResponse;

    public SubscriptionResponse(String subscriptionId, String subscriptionPack, String name, String subscriptionStatus, String campaignId, String ageOfBeneficiary,
                                String estimatedDateOfDelivery, String dateOfBirth, LocationResponse locationResponse) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPack = subscriptionPack;
        this.name = name;
        this.subscriptionStatus = subscriptionStatus;
        this.campaignId = campaignId;
        this.ageOfBeneficiary = ageOfBeneficiary;
        this.estimatedDateOfDelivery = estimatedDateOfDelivery;
        this.dateOfBirth = dateOfBirth;
        this.locationResponse = locationResponse;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriptionPack() {
        return subscriptionPack;
    }

    public String getName() {
        return name;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getAgeOfBeneficiary() {
        return ageOfBeneficiary;
    }

    public String getEstimatedDateOfDelivery() {
        return estimatedDateOfDelivery;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }
}
