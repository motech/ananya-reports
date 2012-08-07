package org.motechproject.ananya.kilkari.reports.domain;

import org.joda.time.DateTime;

import java.sql.Timestamp;

public class WeekNumber {
    public static Integer getSubscriptionWeekNumber(Timestamp startDateTimestamp, DateTime endDate, String pack) {
        DateTime startDate = new DateTime(startDateTimestamp);
        if(startDate.isAfter(endDate))
            return null;
        int diffInWeeks = (int) ((endDate.minus(startDate.getMillis())).getMillis() / (7 * 24 * 60 * 60 * 1000));
        return SubscriptionPack.getFor(pack).getWeekNumber() + diffInWeeks;
    }

    public static int getStartingWeekNumberFor(String pack) {
        return SubscriptionPack.getFor(pack).getWeekNumber();
    }
}
