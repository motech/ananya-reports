ALTER TABLE report.campaign_schedule_alerts DROP COLUMN scheduled_time;

ALTER TABLE report.campaign_schedule_alerts ADD COLUMN date_id Integer;

ALTER TABLE report.campaign_schedule_alerts ADD CONSTRAINT fk_schedule_alert_dimension_time_dimension
FOREIGN KEY(date_id) REFERENCES report.time_dimension(id);

ALTER TABLE report.campaign_schedule_alerts ADD COLUMN time_id Integer;

ALTER TABLE report.campaign_schedule_alerts ADD CONSTRAINT fk_schedule_alert_dimension_hour_dimension
FOREIGN KEY(time_id) REFERENCES report.hour_dimension(id);