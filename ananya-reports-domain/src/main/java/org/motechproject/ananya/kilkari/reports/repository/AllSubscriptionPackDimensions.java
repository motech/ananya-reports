package org.motechproject.ananya.kilkari.reports.repository;

import org.apache.commons.lang.StringUtils;
import org.motechproject.ananya.kilkari.reports.domain.dimension.SubscriptionPackDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSubscriptionPackDimensions {
    @Autowired
    private DataAccessTemplate template;

    public AllSubscriptionPackDimensions() {
    }

    public SubscriptionPackDimension fetchFor(String subscriptionPack) {
        return (SubscriptionPackDimension) template.getUniqueResult(SubscriptionPackDimension.FIND_BY_PACK_NAME,
                new String[]{"subscriptionPack"}, new Object[]{StringUtils.upperCase(subscriptionPack)});
    }
}
