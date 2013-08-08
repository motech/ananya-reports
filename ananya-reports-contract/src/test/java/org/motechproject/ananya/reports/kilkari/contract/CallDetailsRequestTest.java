package org.motechproject.ananya.reports.kilkari.contract;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailRecordRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;

import static junit.framework.Assert.assertEquals;

public class CallDetailsRequestTest {

    @Test
    public void shouldGetDuration() {
        Integer actualDuration = new CallDetailsReportRequest("subscriptionId", "9876543210", "1", "HELP", "SUCCESS", new CallDetailRecordRequest(DateTime.now(), DateTime.now().plusMinutes(2)), "OBD").getDuration();
        assertEquals((Integer) 120, actualDuration);
    }
}
