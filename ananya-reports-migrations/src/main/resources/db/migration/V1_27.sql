ALTER TABLE report.subscribers ADD COLUMN start_week_number INTEGER;
ALTER TABLE report.subscriptions RENAME week_number TO campaign_id;