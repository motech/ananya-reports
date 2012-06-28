package org.motechproject.ananya.kilkari.reports.domain;

import org.joda.time.DateTime;

public class WeekNumber {
    public static int getSubscriptionWeekNumber(DateTime startDate, DateTime endDate, String pack) {
        int diffInWeeks = (int) ((endDate.minus(startDate.getMillis())).getMillis() / (7 * 24 * 60 * 60 * 1000));
        return SubscriptionPack.getFor(pack).getWeekNumber() + diffInWeeks;
    }

    public static int getStartingWeekNumberFor(String pack) {
        return SubscriptionPack.getFor(pack).getWeekNumber();
    }
}
