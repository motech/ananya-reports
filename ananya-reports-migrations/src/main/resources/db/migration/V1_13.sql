ALTER TABLE report.subscriptions 
  ADD COLUMN last_modified_time TIMESTAMP WITH TIME ZONE;

ALTER TABLE report.subscriptions 
  ADD COLUMN week_number INTEGER; 

ALTER TABLE report.subscriptions 
  ADD COLUMN subscription_status VARCHAR(255); 