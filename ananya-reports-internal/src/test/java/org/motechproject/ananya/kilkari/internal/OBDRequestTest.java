package org.motechproject.ananya.kilkari.internal;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class OBDRequestTest {

    @Test
    public void shouldGetDuration() {
        Integer actualDuration = new OBDRequest("subscriptionId", "9876543210", "1", "HELP", "01-01-2001 09:10:00", "01-01-2001 09:40:00", "2", "SUCCESS").getDuration();
        assertEquals((Integer) 1800, actualDuration);
    }
}
