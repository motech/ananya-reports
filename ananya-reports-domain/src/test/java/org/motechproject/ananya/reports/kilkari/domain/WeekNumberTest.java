package org.motechproject.ananya.reports.kilkari.domain;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WeekNumberTest {
    @Test
    public void shouldCalculateWeekNumberBasedOnPackAndReferenceDate() {
        DateTime startDate = new DateTime(2012, 01, 01, 10, 10);
        Integer subscriptionWeekNumber = WeekNumber.getSubscriptionWeekNumber(startDate, new DateTime(2012, 02, 01, 10, 10), SubscriptionPack.NAVJAAT_KILKARI.name(), SubscriptionStatus.ACTIVE.name());
        assertEquals(Integer.valueOf(21), subscriptionWeekNumber);
    }

    @Test
    public void shouldReturnNullIfSubscriptionStatusIsPendingActivation() {
        assertNull(WeekNumber.getSubscriptionWeekNumber(DateTime.now(), DateTime.now().plusDays(2), SubscriptionPack.NANHI_KILKARI.name(), SubscriptionStatus.PENDING_ACTIVATION.name()));
    }
}
