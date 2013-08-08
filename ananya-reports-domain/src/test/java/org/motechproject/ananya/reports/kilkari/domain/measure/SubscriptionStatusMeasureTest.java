package org.motechproject.ananya.reports.kilkari.domain.measure;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.TimeDimension;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SubscriptionStatusMeasureTest {
    @Test
    public void shouldCompareTwoStatusMeasuresBasedOnDateTime() {
        DateTime createdNow =  DateTime.now();
        DateTime createdBefore =  DateTime.now().minusHours(4);
        SubscriptionStatusMeasure newStatusMeasure = new SubscriptionStatusMeasure(null, null, 3, null, null, null, null, new SubscriptionPackDimension("BARI_KILKARI"), new DateDimension(createdNow), new TimeDimension(createdNow), createdNow);
        SubscriptionStatusMeasure oldStatusMeasure = new SubscriptionStatusMeasure(null, null, 3, null, null, null, null, new SubscriptionPackDimension("BARI_KILKARI"), new DateDimension(createdBefore), new TimeDimension(createdBefore), createdBefore);

        assertTrue(oldStatusMeasure.isCreatedBefore(newStatusMeasure));
        assertFalse(newStatusMeasure.isCreatedBefore(oldStatusMeasure));
    }
}
