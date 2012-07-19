package org.motechproject.ananya.kilkari.reports.domain.measure;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.DateDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.TimeDimension;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SubscriptionStatusMeasureTest {
    @Test
    public void shouldCompareTwoStatusMeasuresBasedOnDateTime() {
        DateTime createdNow =  DateTime.now();
        DateTime createdBefore =  DateTime.now().minusHours(4);
        SubscriptionStatusMeasure newStatusMeasure = new SubscriptionStatusMeasure(null, null, 3, null, null, null, null, new SubscriptionPackDimension("FIFTEEN_MONTHS"), new DateDimension(createdNow), new TimeDimension(createdNow));
        SubscriptionStatusMeasure oldStatusMeasure = new SubscriptionStatusMeasure(null, null, 3, null, null, null, null, new SubscriptionPackDimension("FIFTEEN_MONTHS"), new DateDimension(createdBefore), new TimeDimension(createdBefore));

        assertTrue(oldStatusMeasure.isCreatedBefore(newStatusMeasure));
        assertFalse(newStatusMeasure.isCreatedBefore(oldStatusMeasure));
    }
}
