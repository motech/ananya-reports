package org.motechproject.ananya.reports.kilkari.domain.dimension;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "date_id")
    private DateDimension dateId;

    @OneToOne
    @JoinColumn(name = "time_id")
    private TimeDimension timeId;


    public CampaignScheduleAlertDetails() {
    }

    public CampaignScheduleAlertDetails(Subscription subscription, CampaignDimension campaignId, DateDimension dateId, TimeDimension timeId) {
        this.subscription = subscription;
        this.campaignId = campaignId;
        this.dateId = dateId;
        this.timeId = timeId;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public CampaignDimension getCampaignId() {
        return campaignId;
    }

    public DateDimension getDateId() {
        return dateId;
    }

    public TimeDimension getTimeId() {
        return timeId;
    }
}