ALTER TABLE report.subscriber_call_measure
  ADD FOREIGN KEY (start_date) REFERENCES report.time_dimension ;

ALTER TABLE report.subscriber_call_measure
  ADD FOREIGN KEY (start_time) REFERENCES report.hour_dimension;

ALTER TABLE report.subscriber_call_measure
  ADD FOREIGN KEY (end_date) REFERENCES report.time_dimension;

ALTER TABLE report.subscriber_call_measure
  ADD FOREIGN KEY (end_time) REFERENCES report.hour_dimension;