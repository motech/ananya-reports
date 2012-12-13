package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignScheduleAlertRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignScheduleAlertDetails;
import org.motechproject.ananya.reports.kilkari.repository.AllCampaignDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllCampaignScheduleAlerts;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CampaignScheduleAlertService {

    private AllCampaignScheduleAlerts allCampaignScheduleAlerts;
    private AllSubscriptions allSubscriptions;
    private AllCampaignDimensions allCampaignDimensions;

    public CampaignScheduleAlertService() {
    }

    @Autowired
    public CampaignScheduleAlertService(AllCampaignScheduleAlerts allCampaignScheduleAlerts, AllSubscriptions allSubscriptions, AllCampaignDimensions allCampaignDimensions) {
        this.allCampaignScheduleAlerts = allCampaignScheduleAlerts;
        this.allSubscriptions = allSubscriptions;
        this.allCampaignDimensions = allCampaignDimensions;
    }

    public void createCampaignScheduleAlert(CampaignScheduleAlertRequest campaignScheduleAlertRequest) {
        DateTime scheduledTime = campaignScheduleAlertRequest.getScheduledTime();
        CampaignDimension campaignDimension = allCampaignDimensions.fetchFor(campaignScheduleAlertRequest.getCampaignId());
        CampaignScheduleAlertDetails campaignScheduleAlertDetails = new CampaignScheduleAlertDetails(
                allSubscriptions.findBySubscriptionId(campaignScheduleAlertRequest.getSubscriptionId()),
                campaignDimension,
                campaignScheduleAlertRequest.getScheduledTime());
        allCampaignScheduleAlerts.save(campaignScheduleAlertDetails);
    }
}