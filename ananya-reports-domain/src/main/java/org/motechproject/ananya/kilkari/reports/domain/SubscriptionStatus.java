package org.motechproject.ananya.kilkari.reports.domain;

import org.apache.commons.lang.StringUtils;

public enum SubscriptionStatus {
    NEW, NEW_EARLY, PENDING_ACTIVATION, ACTIVATION_FAILED, COMPLETED, ACTIVE, DEACTIVATION_REQUEST_RECEIVED, PENDING_DEACTIVATION, DEACTIVATED, SUSPENDED, PENDING_COMPLETION;

    public static SubscriptionStatus from(String status) {
        return SubscriptionStatus.valueOf(StringUtils.trimToEmpty(status).toUpperCase());
    }

    public static boolean hasNotBeenActivated(String status) {
        SubscriptionStatus subscriptionStatus = from(status);
        return subscriptionStatus.equals(NEW) || subscriptionStatus.equals(PENDING_ACTIVATION) || subscriptionStatus.equals(NEW_EARLY) ? true : false;
    }
}
