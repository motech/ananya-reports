ALTER TABLE report.campaign_dimension
RENAME message_duration TO obd_message_duration;

ALTER TABLE report.campaign_dimension
ADD COLUMN inbox_message_duration INTEGER;

UPDATE report.campaign_dimension
SET inbox_message_duration = 240;

ALTER TABLE report.campaign_dimension
ALTER COLUMN inbox_message_duration SET NOT NULL;