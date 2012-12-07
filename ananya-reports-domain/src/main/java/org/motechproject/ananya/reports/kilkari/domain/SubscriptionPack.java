package org.motechproject.ananya.reports.kilkari.domain;

import org.apache.commons.lang.StringUtils;

public enum SubscriptionPack {
    BARI_KILKARI(1), NAVJAAT_KILKARI(17), NANHI_KILKARI(37);

    private Integer startWeek;

    SubscriptionPack(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public static SubscriptionPack getFor(String pack) {
        return SubscriptionPack.valueOf(StringUtils.trimToEmpty(pack).toUpperCase());
    }
}
