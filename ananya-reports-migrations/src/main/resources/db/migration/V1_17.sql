UPDATE report.subscription_pack_dimension
SET subscription_pack = 'BARI_KILKARI'
WHERE subscription_pack = 'fifteen_months';

UPDATE report.subscription_pack_dimension
SET subscription_pack = 'CHOTI_KILKARI'
WHERE subscription_pack = 'twelve_months';

UPDATE report.subscription_pack_dimension
SET subscription_pack = 'NANHI_KILKARI'
WHERE subscription_pack = 'seven_months';