package org.motechproject.ananya.reports.kilkari.domain;


import org.apache.commons.lang.StringUtils;

public enum CampaignMessageCallSource {
    OBD, INBOX;

    public static Boolean isOBDCall(String callSource){
        return OBD.name().equalsIgnoreCase(StringUtils.trimToEmpty(callSource));
    }
}
