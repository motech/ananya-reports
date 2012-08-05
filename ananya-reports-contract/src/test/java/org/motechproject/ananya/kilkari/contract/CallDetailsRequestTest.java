package org.motechproject.ananya.kilkari.contract;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.kilkari.contract.request.CallDetailsRequest;

import static junit.framework.Assert.assertEquals;

public class CallDetailsRequestTest {

    @Test
    public void shouldGetDuration() {
        Integer actualDuration = new CallDetailsRequest("subscriptionId", "9876543210", "1", "HELP", DateTime.now(), DateTime.now().plusMinutes(2), "2", "SUCCESS", "OBD").getDuration();
        assertEquals((Integer) 120, actualDuration);
    }
}
