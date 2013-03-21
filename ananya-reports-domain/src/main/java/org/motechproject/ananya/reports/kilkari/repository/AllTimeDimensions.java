package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.motechproject.ananya.reports.kilkari.domain.dimension.TimeDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllTimeDimensions {
    @Autowired
    private DataAccessTemplate template;
    private static final DateTimeZone ISTTimeZone = DateTimeZone.forID("Asia/Calcutta");

    public TimeDimension fetchFor(DateTime dateTime) {
        DateTime dateWithTimeZone = dateTime.withZone(ISTTimeZone);
        int hourOfDay = dateWithTimeZone.getHourOfDay();
        int minuteOfHour = dateWithTimeZone.getMinuteOfHour();

        return (TimeDimension) template.getUniqueResult(
                TimeDimension.FIND_BY_HOUR_AND_MINUTE,
                new String[]{"hour_of_day", "minute_of_hour"},
                new Object[]{hourOfDay, minuteOfHour});
    }
}
