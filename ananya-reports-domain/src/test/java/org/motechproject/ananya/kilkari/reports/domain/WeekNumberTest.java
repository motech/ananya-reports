package org.motechproject.ananya.kilkari.reports.domain;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeekNumberTest {
    @Test
    public void shouldCalculateWeekNumberBasedOnPackAndReferenceDate() {
        int subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(new DateTime(2012, 01, 01, 10, 10), new DateTime(2012, 02, 01, 10, 10), SubscriptionPack.TWELVE_MONTHS.name());
        assertEquals(17, subscriptionWeekNumber);
    }

    @Test
    public void shouldGiveWeekNumberBasedOnPack() {
        int subscriptionWeekNumber = WeekNumber.getStartingWeekNumberFor("TWELVE_MONTHS");
        assertEquals(13, subscriptionWeekNumber);
    }
}
