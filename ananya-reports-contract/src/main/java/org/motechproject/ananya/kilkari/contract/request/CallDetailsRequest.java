package org.motechproject.ananya.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class CallDetailsRequest {
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String msisdn;
    @JsonProperty
    private String campaignId;
    @JsonProperty
    private String serviceOption;
    @JsonProperty
    private String retryCount;
    @JsonProperty
    private String status;
    @JsonProperty
    private CallDetailRecordRequest callDetailRecord;
    @JsonProperty
    private String callSource;


    public CallDetailsRequest(String subscriptionId, String msisdn, String campaignId, String serviceOption, String retryCount, String status, CallDetailRecordRequest callDetailRecord, String callSource) {
        this.subscriptionId = subscriptionId;
        this.msisdn = msisdn;
        this.campaignId = campaignId;
        this.serviceOption = serviceOption;
        this.retryCount = retryCount;
        this.status = status;
        this.callSource = callSource;
        this.callDetailRecord = callDetailRecord;
    }

    public CallDetailsRequest() {
    }

    @JsonIgnore
    public String getMsisdn() {
        return msisdn;
    }

    @JsonIgnore
    public Integer getDuration() {
        return (int) ((getEndTime().getMillis() - getStartTime().getMillis()) / 1000);
    }

    @JsonIgnore
    public String getCampaignId() {
        return campaignId;
    }

    @JsonIgnore
    public String getServiceOption() {
        return serviceOption;
    }

    @JsonIgnore
    public DateTime getStartTime() {
        return callDetailRecord.getStartTime();
    }

    @JsonIgnore
    public DateTime getEndTime() {
        return callDetailRecord.getEndTime();
    }

    @JsonIgnore
    public String getSubscriptionId() {
        return subscriptionId;
    }

    @JsonIgnore
    public String getRetryCount() {
        return retryCount;
    }

    @JsonIgnore
    public String getStatus() {
        return status;
    }

    public String getCallSource() {
        return callSource;
    }
}

