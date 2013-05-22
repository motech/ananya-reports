ALTER TABLE report.location_dimension ADD COLUMN state VARCHAR(255);
UPDATE report.location_dimension set state = 'BIHAR';
ALTER TABLE report.location_dimension alter COLUMN state set NOT NULL;
ALTER TABLE report.location_dimension DROP CONSTRAINT unq_district_block_panchayat;
ALTER TABLE report.location_dimension ADD CONSTRAINT unq_state_district_block_panchayat UNIQUE (state, district, block, panchayat);
