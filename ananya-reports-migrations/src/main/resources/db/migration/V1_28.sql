ALTER TABLE report.location_dimension ADD CONSTRAINT unq_district_block_panchayat UNIQUE (district, block, panchayat);
