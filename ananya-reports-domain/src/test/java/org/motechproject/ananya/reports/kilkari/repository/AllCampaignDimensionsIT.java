package org.motechproject.ananya.reports.kilkari.repository;

import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllCampaignDimensionsIT extends SpringIntegrationTest{
    @Autowired
    private AllCampaignDimensions allCampaignDimensions;

    @Test
    public void shouldFetchForGivenCampaignId() {
        String campaignId = "12";
        Integer obdMessageDuration = 20;
        Integer inboxMessageDuration = 10;
        markForDeletion(template.save(new CampaignDimension(campaignId, obdMessageDuration, inboxMessageDuration)));

        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(campaignId);

        assertEquals(obdMessageDuration, campaignDimension.getObdMessageDuration());
        assertEquals(inboxMessageDuration, campaignDimension.getInboxMessageDuration());
    }
}
