package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.motechproject.ananya.reports.kilkari.domain.CampaignMessageCallSource;

import javax.persistence.*;

@Entity
@Table(name = "campaign_dimension")
@NamedQuery(name = CampaignDimension.FIND_BY_CAMPAIGN_ID, query = "select cd from CampaignDimension cd where cd.campaignId=:campaign_id")
public class CampaignDimension {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "campaign_id", nullable = false)
    private String campaignId;

    @Column(name = "obd_message_duration", nullable = false)
    private Integer obdMessageDuration;

    @Column(name = "inbox_message_duration", nullable = false)
    private Integer inboxMessageDuration;

    public static final String FIND_BY_CAMPAIGN_ID = "find.by.campaign.id";

    public CampaignDimension() {
    }

    public CampaignDimension(String campaignId, Integer obdMessageDuration, Integer inboxMessageDuration) {
        this.campaignId = campaignId;
        this.obdMessageDuration = obdMessageDuration;
        this.inboxMessageDuration = inboxMessageDuration;
    }

    public Integer getId() {
        return id;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public Integer getObdMessageDuration() {
        return obdMessageDuration;
    }

    public Integer getInboxMessageDuration() {
        return inboxMessageDuration;
    }
}
