CREATE TABLE report.campaign_schedule_alerts (
    id serial NOT NULL,
    subscription_id Integer,
    campaign_id Integer,
    scheduled_time TIMESTAMP,

    CONSTRAINT fk_schedule_alert_dimension_subscriptions
    FOREIGN KEY(subscription_id) REFERENCES report.subscriptions(id),

    CONSTRAINT fk_schedule_alert_dimension_campaign_dimension
    FOREIGN KEY(subscription_id) REFERENCES report.campaign_dimension(id)
);