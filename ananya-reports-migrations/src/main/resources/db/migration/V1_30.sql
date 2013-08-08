ALTER TABLE report.subscription_status_measure 
  ADD FOREIGN KEY (subscription_id) REFERENCES report.subscriptions; 