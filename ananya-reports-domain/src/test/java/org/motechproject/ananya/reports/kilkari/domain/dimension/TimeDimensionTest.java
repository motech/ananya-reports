package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.joda.time.DateTime;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TimeDimensionTest {

    @Test
    public void shouldCompareTwoTimeDimensionsBasedOnHours() {
        TimeDimension now = new TimeDimension(DateTime.now());
        TimeDimension before = new TimeDimension(DateTime.now().minusHours(3));

        assertEquals(1, now.compareTo(before));
        assertEquals(-1, before.compareTo(now));
    }

    @Test
    public void shouldCompareTwoTimeDimensionsBasedOnMinutesIfHoursAreSame() {
        TimeDimension now = new TimeDimension(DateTime.now());
        TimeDimension before = new TimeDimension(DateTime.now().minusMinutes(3));

        assertEquals(1, now.compareTo(before));
        assertEquals(-1, before.compareTo(now));
    }
}
