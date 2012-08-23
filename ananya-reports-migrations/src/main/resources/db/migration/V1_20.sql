ALTER TABLE report.subscribers DROP COLUMN msisdn;
ALTER TABLE report.subscriptions ADD COLUMN msisdn BIGINT NOT NULL DEFAULT(12345) ;


