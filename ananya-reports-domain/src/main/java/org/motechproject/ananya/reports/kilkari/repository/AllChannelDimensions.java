package org.motechproject.ananya.reports.kilkari.repository;


import org.apache.commons.lang.StringUtils;
import org.motechproject.ananya.reports.kilkari.domain.dimension.ChannelDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllChannelDimensions {

    @Autowired
    private DataAccessTemplate template;

    public AllChannelDimensions() {
    }

    public ChannelDimension fetchFor(String channel) {
        return (ChannelDimension) template.getUniqueResult(ChannelDimension.FIND_BY_CHANNEL,
                new String[]{"channel"}, new Object[]{StringUtils.upperCase(channel)});
    }
}
