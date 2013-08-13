package org.motechproject.ananya.reports.kilkari.contract.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

public class CampaignScheduleAlertRequest implements Serializable {
    private static final long serialVersionUID = -5571514413206376873L;
    @JsonProperty
    private String subscriptionId;

    @JsonProperty
    private DateTime scheduledTime;

    @JsonProperty
    private String campaignId;

    public CampaignScheduleAlertRequest() {
    }

    public CampaignScheduleAlertRequest(String subscriptionId, String campaignId, DateTime scheduledTime) {
        this.subscriptionId = subscriptionId;
        this.scheduledTime = scheduledTime;
        this.campaignId = campaignId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public DateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getCampaignId() {
        return campaignId;
    }
}