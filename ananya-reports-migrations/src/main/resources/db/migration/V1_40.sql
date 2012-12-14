ALTER TABLE report.operator_dimension
ADD COLUMN start_pulse INTEGER,
ADD COLUMN end_pulse INTEGER;

UPDATE report.operator_dimension
SET start_pulse=500 , end_pulse=60500 WHERE operator ='RELIANCEGSM';

UPDATE report.operator_dimension
SET start_pulse=500 , end_pulse=60500 WHERE operator ='IDEA';

UPDATE report.operator_dimension
SET start_pulse=0 , end_pulse=60000 WHERE operator ='VODAFONE';

UPDATE report.operator_dimension
SET start_pulse=500 , end_pulse=60500 WHERE operator ='TATADOCOMO';

UPDATE report.operator_dimension
SET start_pulse=500 , end_pulse=60500 WHERE operator ='AIRTEL';

UPDATE report.operator_dimension
SET start_pulse=500 , end_pulse=60500 WHERE operator ='BSNL';