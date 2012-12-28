package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class CallDetailRecordRequest implements Serializable {

    private static final long serialVersionUID = 7262958725223661412L;
    @JsonProperty
    private DateTime startTime;

    @JsonProperty
    private DateTime endTime;

    public CallDetailRecordRequest(DateTime startTime, DateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CallDetailRecordRequest() {
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

