package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class AllDateDimensionsIT extends SpringIntegrationTest {

    @Autowired
    private AllDateDimensions allDateDimensions;

    @Test
    public void shouldFetchDateDimensionInISTForGivenDateTime() {
        DateTime now = new DateTime(2012, 7, 26, 0, 0).withZone(DateTimeZone.UTC);
        int expectedDayOfYear = now.withZone(DateTimeZone.forID("Asia/Calcutta")).getDayOfYear();

        DateDimension dateDimension = allDateDimensions.fetchFor(now);

        assertEquals(expectedDayOfYear, (int) dateDimension.getDay());
    }

}
