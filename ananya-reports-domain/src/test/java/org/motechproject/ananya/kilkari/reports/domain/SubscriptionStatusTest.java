package org.motechproject.ananya.kilkari.reports.domain;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void shouldGetTheDisplayString() {
        assertEquals("New", SubscriptionStatus.NEW.getDisplayString());
        assertEquals("Early Subscripiton", SubscriptionStatus.NEW_EARLY.getDisplayString());
        assertEquals("Pending Subscription",SubscriptionStatus.PENDING_ACTIVATION.getDisplayString());
        assertEquals("Activation Failed",SubscriptionStatus.ACTIVATION_FAILED.getDisplayString());
        assertEquals("Completed",SubscriptionStatus.COMPLETED.getDisplayString());
        assertEquals("Activated",SubscriptionStatus.ACTIVE.getDisplayString());
        assertEquals("Deactivation Requested",SubscriptionStatus.DEACTIVATION_REQUEST_RECEIVED.getDisplayString());
        assertEquals("Pending Deactivation",SubscriptionStatus.PENDING_DEACTIVATION.getDisplayString());
        assertEquals("Deactivated",SubscriptionStatus.DEACTIVATED.getDisplayString());
        assertEquals("Suspended",SubscriptionStatus.SUSPENDED.getDisplayString());
        assertEquals("Pending Completion",SubscriptionStatus.PENDING_COMPLETION.getDisplayString());
    }
}
