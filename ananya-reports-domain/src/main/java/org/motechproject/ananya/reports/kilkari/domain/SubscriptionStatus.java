package org.motechproject.ananya.reports.kilkari.domain;

import org.apache.commons.lang.StringUtils;

public enum SubscriptionStatus {
    NEW("New"), NEW_EARLY("Early Subscription"), PENDING_ACTIVATION("Pending Subscription"), ACTIVATION_FAILED("Activation Failed"),
    COMPLETED("Completed"), ACTIVE("Activated"), DEACTIVATION_REQUEST_RECEIVED("Deactivation Requested"), PENDING_DEACTIVATION("Pending Deactivation"),
    DEACTIVATED("Deactivated"), SUSPENDED("Suspended"), PENDING_COMPLETION("Pending Completion");

    private String displayString;

    private SubscriptionStatus(String displayString) {
        this.displayString = displayString;
    }

    public static SubscriptionStatus from(String status) {
        return SubscriptionStatus.valueOf(StringUtils.trimToEmpty(status).toUpperCase());
    }

    public static boolean hasNotBeenActivated(String status) {
        SubscriptionStatus subscriptionStatus = from(status);
        return subscriptionStatus.equals(NEW) || subscriptionStatus.equals(PENDING_ACTIVATION) || subscriptionStatus.equals(NEW_EARLY) ? true : false;
    }

    public String getDisplayString() {
        return displayString;
    }
}
