package org.motechproject.ananya.kilkari.internal;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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


    public CallDetailsRequest(String subscriptionId, String msisdn, String campaignId, String serviceOption, String startTime, String endTime, String retryCount, String status, String callSource) {
        this.subscriptionId = subscriptionId;
        this.msisdn = msisdn;
        this.campaignId = campaignId;
        this.serviceOption = serviceOption;
        this.retryCount = retryCount;
        this.status = status;
        this.callSource = callSource;
        this.callDetailRecord = new CallDetailRecordRequest(startTime,endTime);
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
        return parseDateTime(callDetailRecord.startTime);
    }

    @JsonIgnore
    public DateTime getEndTime() {
        return parseDateTime(callDetailRecord.endTime);
    }

    private DateTime parseDateTime(String dateTime) {
        return DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss").parseDateTime(dateTime);
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

    public static class CallDetailRecordRequest {
        public CallDetailRecordRequest(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public CallDetailRecordRequest() {
        }

        @JsonProperty
        String startTime;
        @JsonProperty
        String endTime;
    }
}
