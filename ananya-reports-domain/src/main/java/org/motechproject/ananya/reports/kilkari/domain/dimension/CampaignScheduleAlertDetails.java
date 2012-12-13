package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "campaign_schedule_alerts")
public class CampaignScheduleAlertDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToOne
    @JoinColumn(name = "campaign_id")
    private CampaignDimension campaignId;

    @Column(name = "scheduled_time")
    private Timestamp scheduledTime;

    public CampaignScheduleAlertDetails() {
    }

    public CampaignScheduleAlertDetails(Subscription subscription, CampaignDimension campaignId, DateTime scheduledTime) {
        this.subscription = subscription;
        this.campaignId = campaignId;
        this.scheduledTime = new Timestamp(scheduledTime.getMillis());
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public CampaignDimension getCampaignId() {
        return campaignId;
    }

    public Timestamp getScheduledTime() {
        return scheduledTime;
    }
}