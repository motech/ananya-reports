ALTER TABLE report.subscriptions ADD COLUMN referred_by_flag BOOLEAN DEFAULT false;
UPDATE report.subscriptions set referred_by_flag = TRUE where referred_by_flw_msisdn is not null;