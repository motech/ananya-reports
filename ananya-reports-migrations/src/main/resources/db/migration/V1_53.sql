ALTER TABLE report.location_dimension ADD COLUMN state VARCHAR(255) DEFAULT 'Bihar';
UPDATE report.location_dimension set state = 'BIHAR';
ALTER TABLE report.location_dimension alter COLUMN state set NOT NULL;
