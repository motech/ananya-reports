CREATE TABLE report.subscriber_care_requests (
  id         SERIAL       NOT NULL,
  msisdn     VARCHAR(255) NOT NULL,
  reason     VARCHAR(255),
  channel_id INTEGER      NOT NULL,
  date_id    INTEGER      NOT NULL,
  time_id    INTEGER      NOT NULL,

  PRIMARY KEY (id),

  CONSTRAINT fk_care_request_channel_dimension
  FOREIGN KEY (channel_id) REFERENCES report.channel_dimension (id),

  CONSTRAINT fk_care_request_date_dimension
  FOREIGN KEY (date_id) REFERENCES report.time_dimension (id),

  CONSTRAINT fk_care_request_time_dimension
  FOREIGN KEY (time_id) REFERENCES report.hour_dimension (id)


);