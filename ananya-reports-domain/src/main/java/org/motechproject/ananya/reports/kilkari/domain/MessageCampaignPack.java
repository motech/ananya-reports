package org.motechproject.ananya.reports.kilkari.domain;

import org.apache.commons.lang.StringUtils;

public enum MessageCampaignPack {
    BARI_KILKARI,
    NAVJAAT_KILKARI,
    NANHI_KILKARI,
    INFANT_DEATH,
    MISCARRIAGE;

    public static MessageCampaignPack from(SubscriptionPack subscriptionPack) {
        return MessageCampaignPack.valueOf(StringUtils.trimToEmpty(subscriptionPack.name()).toUpperCase());
    }

    public static MessageCampaignPack from(String messageCampaignPack) {
        return MessageCampaignPack.valueOf(StringUtils.trimToEmpty(messageCampaignPack).toUpperCase());
    }
}
