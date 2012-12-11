package org.motechproject.ananya.reports.kilkari.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CampaignMessageCallSourceTest {
    @Test
    public void shouldCheckIfCallSourceIsOBD() throws Exception {
        assertTrue(CampaignMessageCallSource.isOBDCall(" oBd   "));
        assertFalse(CampaignMessageCallSource.isOBDCall("Inbox"));
        assertFalse(CampaignMessageCallSource.isOBDCall(null));
    }
}
