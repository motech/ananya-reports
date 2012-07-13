package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.TimeDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllTimeDimensionsIT extends SpringIntegrationTest {

    @Autowired
    private AllTimeDimensions allTimeDimensions;

    @Test
    public void shouldFetchTimeDimensionForGivenDateTime() {
        DateTime now = DateTime.now();
        Integer hourOfDay = now.getHourOfDay();
        Integer minuteOfHour = now.getMinuteOfHour();

        TimeDimension timeDimension = allTimeDimensions.fetchFor(now);

        assertEquals(hourOfDay, timeDimension.getHourOfDay());
        assertEquals(minuteOfHour, timeDimension.getMinuteOfHour());
    }
}
