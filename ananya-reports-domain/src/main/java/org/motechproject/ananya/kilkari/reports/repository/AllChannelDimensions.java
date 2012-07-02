package org.motechproject.ananya.kilkari.reports.repository;


import org.motechproject.ananya.kilkari.reports.domain.dimension.ChannelDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AllChannelDimensions {

    @Autowired
    private DataAccessTemplate template;

    public AllChannelDimensions() {
    }

    public ChannelDimension fetchFor(String channel) {
        return (ChannelDimension) template.getUniqueResult(ChannelDimension.FIND_BY_CHANNEL,
                new String[]{"channel"}, new Object[]{channel});
    }
}
