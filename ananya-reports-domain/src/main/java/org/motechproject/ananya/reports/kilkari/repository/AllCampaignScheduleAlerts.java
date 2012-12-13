package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignScheduleAlertDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllCampaignScheduleAlerts {

    @Autowired
    private DataAccessTemplate template;

    public void save(CampaignScheduleAlertDetails campaignScheduleAlertDetails) {
        template.save(campaignScheduleAlertDetails);
    }
}
