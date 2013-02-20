CREATE INDEX status_idx
  ON report.subscription_status_measure
  USING btree
  (status COLLATE pg_catalog."default");

CREATE INDEX "subscriptionIdx"
  ON report.subscription_status_measure
  USING btree
  (last_modified_time, subscription_id);

CREATE INDEX msisdn_idx
  ON report.subscriptions
  USING btree
  (msisdn);

