package org.motechproject.ananya.reports.kilkari.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.ananya.reports.kilkari.domain.dimension.CampaignScheduleAlertDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllCampaignScheduleAlerts {

    @Autowired
    private DataAccessTemplate template;

    public void save(CampaignScheduleAlertDetails campaignScheduleAlertDetails) {
        template.save(campaignScheduleAlertDetails);
    }

    public void deleteFor(Long msisdn) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignScheduleAlertDetails.class);
        criteria.createAlias("subscription", "s");
        criteria.add(Restrictions.eq("s.msisdn", msisdn));
        List<CampaignScheduleAlertDetails> campaignScheduleAlerts = template.findByCriteria(criteria);
        template.deleteAll(campaignScheduleAlerts);
    }
}
