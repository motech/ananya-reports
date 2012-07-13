package org.motechproject.ananya.kilkari.internal;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class OBDRequest {
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String msisdn;
    @JsonProperty
    private String campaignId;
    @JsonProperty
    private String serviceOption;
    @JsonProperty
    private CallDetailRecordRequest callDetailRecord;

    public OBDRequest(String subscriptionId, String msisdn, String campaignId, String serviceOption, String startTime, String endTime) {
        this.subscriptionId = subscriptionId;
        this.msisdn = msisdn;
        this.campaignId = campaignId;
        this.serviceOption = serviceOption;
        this.callDetailRecord = new CallDetailRecordRequest(startTime,endTime);
    }

    public OBDRequest() {
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
        return DateTimeFormat.forPattern("dd-MM-yyyy hh:mm:ss").parseDateTime(dateTime);
    }

    @JsonIgnore
    public String getSubscriptionId() {
        return subscriptionId;
    }

    public static class CallDetailRecordRequest {
        public CallDetailRecordRequest(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @JsonProperty
        String startTime;
        @JsonProperty
        String endTime;
    }
}
