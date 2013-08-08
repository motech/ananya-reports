-- New Tables
CREATE TABLE report.campaign_dimension(
    id serial NOT NULL,
    campaign_id VARCHAR NOT NULL,
    message_duration INTEGER NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE report.hour_dimension(
    id serial NOT NULL,
    hour_of_day INTEGER NOT NULL,
    minute_of_hour INTEGER NOT NULL,
    PRIMARY KEY(id)
);

-- New Columns
ALTER TABLE report.subscriber_call_measure
  ADD COLUMN service_option VARCHAR;

ALTER TABLE report.subscriber_call_measure
  ADD COLUMN percentage_listened INTEGER;

ALTER TABLE report.subscriber_call_measure
  ADD COLUMN campaign_id INTEGER NOT NULL;

ALTER TABLE report.subscriber_call_measure
  ADD COLUMN start_date INTEGER NOT NULL;

ALTER TABLE report.subscriber_call_measure
  ADD COLUMN start_time INTEGER NOT NULL;

ALTER TABLE report.subscriber_call_measure
  ADD COLUMN end_date INTEGER NOT NULL;

ALTER TABLE report.subscriber_call_measure
  ADD COLUMN end_time INTEGER NOT NULL;

-- New Constraints
ALTER TABLE report.subscriber_call_measure
  ADD CONSTRAINT fk_subscriber_call_measure_campaign_dimension
    FOREIGN KEY(campaign_id) REFERENCES report.campaign_dimension(id);

-- Drop columns
ALTER TABLE report.subscriber_call_measure
  DROP COLUMN ring_duration;

ALTER TABLE report.subscriber_call_measure
  DROP COLUMN channel_id;

ALTER TABLE report.subscriber_call_measure
  DROP COLUMN time_id;

