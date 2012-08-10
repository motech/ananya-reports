package org.motechproject.ananya.reports.kilkari.domain.dimension;

import javax.persistence.*;

@Entity
@Table(name = "campaign_dimension")
@NamedQuery(name = CampaignDimension.FIND_BY_CAMPAIGN_ID, query = "select cd from CampaignDimension cd where cd.campaignId=:campaign_id")
public class CampaignDimension {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "campaign_id")
    private String campaignId;

    @Column(name = "message_duration")
    private Integer messageDuration;

    public static final String FIND_BY_CAMPAIGN_ID = "find.by.campaign.id";

    public CampaignDimension() {
    }

    public CampaignDimension(String campaignId, Integer messageDuration) {
        this.campaignId = campaignId;
        this.messageDuration = messageDuration;
    }

    public Integer getId() {
        return id;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public Integer getMessageDuration() {
        return messageDuration;
    }
}
