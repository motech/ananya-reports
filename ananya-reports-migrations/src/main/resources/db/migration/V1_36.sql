UPDATE  report.location_dimension
SET district = UPPER(district),
    block = UPPER(block),
    panchayat = UPPER(panchayat);