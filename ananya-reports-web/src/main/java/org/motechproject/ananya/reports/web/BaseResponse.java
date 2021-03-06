package org.motechproject.ananya.reports.web;

import com.google.gson.Gson;
import org.codehaus.jackson.annotate.JsonProperty;

public class BaseResponse {

    private static final String ERROR = "ERROR";
    private static final String SUCCESS = "SUCCESS";

    @JsonProperty
    protected String status;
    @JsonProperty
    protected String description;

    public BaseResponse(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public BaseResponse() {
    }

    public static BaseResponse failure(String description) {
        return new BaseResponse(ERROR, description);
    }

    public static BaseResponse success(String description) {
        return new BaseResponse(SUCCESS, description);
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseResponse)) return false;

        BaseResponse that = (BaseResponse) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return !(status != null ? !status.equals(that.status) : that.status != null);
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public boolean isError() {
        return status.equals(ERROR);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
