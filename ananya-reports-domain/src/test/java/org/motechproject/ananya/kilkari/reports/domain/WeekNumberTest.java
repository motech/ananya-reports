package org.motechproject.ananya.kilkari.reports.domain;

import org.joda.time.DateTime;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class WeekNumberTest {
    @Test
    public void shouldCalculateWeekNumberBasedOnPackAndReferenceDate() {
        DateTime startDate = new DateTime(2012, 01, 01, 10, 10);
        Integer subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(new Timestamp(startDate.getMillis()), new DateTime(2012, 02, 01, 10, 10), SubscriptionPack.TWELVE_MONTHS.name());
        assertEquals(Integer.valueOf(17), subscriptionWeekNumber);
    }

    @Test
    public void shouldReturnNullIfStartDateIsAfterCreatedAtDateToHandleEarlySubscriptionCases() {
        DateTime startDate = new DateTime(2012, 03, 01, 10, 10);
        Integer subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(new Timestamp(startDate.getMillis()), new DateTime(2012, 02, 01, 10, 10), SubscriptionPack.TWELVE_MONTHS.name());
        assertEquals(null, subscriptionWeekNumber);
    }

    @Test
    public void shouldGiveWeekNumberBasedOnPack() {
        int subscriptionWeekNumber = WeekNumber.getStartingWeekNumberFor("TWELVE_MONTHS");
        assertEquals(13, subscriptionWeekNumber);
    }
}
