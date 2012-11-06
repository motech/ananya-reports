package org.motechproject.ananya.reports.kilkari.domain;

import org.apache.commons.lang.StringUtils;

public enum SubscriptionPack {
    BARI_KILKARI(1), NAVJAAT_KILKARI(13), NANHI_KILKARI(33);

    private Integer weekNumber;

    SubscriptionPack(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public static SubscriptionPack getFor(String pack) {
        return SubscriptionPack.valueOf(StringUtils.trimToEmpty(pack).toUpperCase());
    }
}
