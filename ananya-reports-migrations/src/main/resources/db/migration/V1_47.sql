ALTER TABLE report.subscription_pack_dimension ADD COLUMN subscription_pack_alias VARCHAR(255);

UPDATE report.subscription_pack_dimension
SET subscription_pack_alias = '16 Month'
WHERE subscription_pack = 'BARI_KILKARI';