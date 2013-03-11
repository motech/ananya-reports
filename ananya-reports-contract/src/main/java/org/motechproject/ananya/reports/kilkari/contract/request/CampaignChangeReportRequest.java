package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class CampaignChangeReportRequest implements Serializable {
    private static final long serialVersionUID = 6182554392742952876L;

    @JsonProperty
    private String messageCampaignPack;
    @JsonProperty
    private DateTime createdAt;

    public CampaignChangeReportRequest() {
    }

    public CampaignChangeReportRequest(String messageCampaignPack, DateTime createdAt) {
        this.messageCampaignPack = messageCampaignPack;
        this.createdAt = createdAt;
    }

    public String getMessageCampaignPack() {
        return messageCampaignPack;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
