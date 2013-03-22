package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class SubscriberCareReportRequest implements Serializable {

    private static final long serialVersionUID = 7215949656794757860L;
    @JsonProperty
    private String msisdn;
    @JsonProperty
    private String reason;
    @JsonProperty
    private String channel;
    @JsonProperty
    private DateTime createdAt;

    public SubscriberCareReportRequest() {
    }

    public SubscriberCareReportRequest(String msisdn, String reason, String channel, DateTime createdAt) {
        this.msisdn = msisdn;
        this.reason = reason;
        this.channel = channel;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonIgnore
    public String getMsisdn() {
        return msisdn;
    }

    @JsonIgnore
    public String getReason() {
        return reason;
    }

    @JsonIgnore
    public String getChannel() {
        return channel;
    }

    @JsonIgnore
    public DateTime getCreatedAt() {
        return createdAt;
    }
}
