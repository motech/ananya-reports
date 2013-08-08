CREATE INDEX subscriptionId_status
ON report.subscription_status_measure
USING btree
(subscription_id, status COLLATE pg_catalog."default");

CREATE INDEX lastModifiedTime_subscriptionId
ON report.subscription_status_measure
USING btree
(last_modified_time,subscription_id);

CREATE INDEX msisdn_idx
  ON report.subscriptions
  USING btree
  (msisdn);
