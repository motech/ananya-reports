package org.motechproject.ananya.kilkari.reports.domain;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SubscriptionStatusTest {
    @Test
    public void subscriptionHasNotBeenActivatedIfItIsInNewOrPendingActivationState() {
        for (SubscriptionStatus status : SubscriptionStatus.values()) {
            if (status.name().equals("NEW") || status.name().equals("PENDING_ACTIVATION") || status.name().equals("NEW_EARLY"))
                assertTrue(SubscriptionStatus.hasNotBeenActivated(status.name()));
            else
                assertFalse(SubscriptionStatus.hasNotBeenActivated(status.name()));
        }
    }
}
