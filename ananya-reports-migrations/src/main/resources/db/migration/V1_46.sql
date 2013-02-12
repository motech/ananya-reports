ALTER TABLE report.subscription_pack_dimension ADD COLUMN subscription_pack_alias VARCHAR(255);

UPDATE report.subscription_pack_dimension
SET subscription_pack_alias = '15 Month'
WHERE subscription_pack = 'BARI_KILKARI';

UPDATE report.subscription_pack_dimension
SET subscription_pack_alias = '12 Month'
WHERE subscription_pack = 'NAVJAAT_KILKARI';

UPDATE report.subscription_pack_dimension
SET subscription_pack_alias = '7 Month'
WHERE subscription_pack = 'NANHI_KILKARI';