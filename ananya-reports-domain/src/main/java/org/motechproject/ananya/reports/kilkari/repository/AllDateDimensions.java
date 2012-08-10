package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllDateDimensions {
    @Autowired
    private DataAccessTemplate template;

    public DateDimension makeFor(DateTime dateTime) {
        DateDimension dateDimension = new DateDimension(dateTime);
        template.save(dateDimension);
        return dateDimension;
    }

    public DateDimension fetchFor(DateTime dateTime) {
        return (DateDimension) template.getUniqueResult(
                DateDimension.FIND_BY_DAY_MONTH_YEAR,
                new String[]{"year", "month", "day"},
                new Object[]{dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfYear()});
    }
}
