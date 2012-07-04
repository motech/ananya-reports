package org.motechproject.ananya.kilkari.reports.domain;

import org.apache.commons.lang.StringUtils;

public enum SubscriptionPack {
    FIFTEEN_MONTHS(1), TWELVE_MONTHS(13), SEVEN_MONTHS(33);

    private int weekNumber;

    SubscriptionPack(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public static SubscriptionPack getFor(String pack) {
        return SubscriptionPack.valueOf(StringUtils.trimToEmpty(pack).toUpperCase());
    }

    public static boolean isValid(String subscriptionPack) {
        return (subscriptionPack != null && SubscriptionPack.contains(subscriptionPack));
    }

    private static boolean contains(String value) {
        for (SubscriptionPack subscriptionPack : SubscriptionPack.values()) {
            if (subscriptionPack.name().equals(StringUtils.trimToEmpty(value).toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}