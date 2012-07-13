package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.reports.domain.dimension.TimeDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllTimeDimensions {
    @Autowired
    private DataAccessTemplate template;

    public TimeDimension fetchFor(DateTime dateTime) {
        int hourOfDay = dateTime.getHourOfDay();
        int minuteOfHour = dateTime.getMinuteOfHour();

        return (TimeDimension) template.getUniqueResult(
                TimeDimension.FIND_BY_HOUR_AND_MINUTE,
                new String[]{"hour_of_day", "minute_of_hour"},
                new Object[]{hourOfDay, minuteOfHour});
    }
}
