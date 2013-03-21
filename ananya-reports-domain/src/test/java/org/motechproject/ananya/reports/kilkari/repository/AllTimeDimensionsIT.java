package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.TimeDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllTimeDimensionsIT extends SpringIntegrationTest {

    @Autowired
    private AllTimeDimensions allTimeDimensions;

    @Test
    public void shouldFetchTimeDimensionInISTForGivenDateTime() {
        DateTime now = DateTime.now().withZone(DateTimeZone.UTC);
        Integer expectedHourOfDay = now.withZone(DateTimeZone.forID("Asia/Calcutta")).getHourOfDay();
        Integer expectedMinuteOfHour = now.withZone(DateTimeZone.forID("Asia/Calcutta")).getMinuteOfHour();

        TimeDimension timeDimension = allTimeDimensions.fetchFor(now);

        assertEquals(expectedHourOfDay, timeDimension.getHourOfDay());
        assertEquals(expectedMinuteOfHour, timeDimension.getMinuteOfHour());
    }
}
