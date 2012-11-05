ALTER TABLE report.location_dimension ADD COLUMN status VARCHAR(36);
UPDATE report.location_dimension SET status = 'VALID';
ALTER TABLE report.location_dimension ALTER status SET NOT NULL;