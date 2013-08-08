package org.motechproject.ananya.reports.smoke.contract;

import org.codehaus.jackson.annotate.JsonProperty;

public class BaseResponse {
    @JsonProperty
    private String status;
    @JsonProperty
    private String description;

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
