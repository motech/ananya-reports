package org.motechproject.ananya.reports.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class CallDetailRecordRequest implements Serializable {

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
}

