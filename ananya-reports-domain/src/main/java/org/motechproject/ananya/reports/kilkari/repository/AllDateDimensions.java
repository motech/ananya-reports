package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllDateDimensions {
    @Autowired
    private DataAccessTemplate template;
    private static final DateTimeZone ISTTimeZone = DateTimeZone.forID("Asia/Calcutta");

    public DateDimension fetchFor(DateTime dateTime) {
        DateTime dateWithTimeZone = dateTime.withZone(ISTTimeZone);
        return (DateDimension) template.getUniqueResult(
                DateDimension.FIND_BY_DAY_MONTH_YEAR,
                new String[]{"year", "month", "day"},
                new Object[]{dateWithTimeZone.getYear(), dateWithTimeZone.getMonthOfYear(), dateWithTimeZone.getDayOfYear()});
    }
}
