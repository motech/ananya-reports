package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllCampaignDimensions {
    @Autowired
    private DataAccessTemplate template;

    public CampaignDimension fetchFor(String campaignId) {
        return (CampaignDimension) template.getUniqueResult(
                CampaignDimension.FIND_BY_CAMPAIGN_ID,
                new String[] {"campaign_id"},
                new Object[] {campaignId}
        );
    }
}
