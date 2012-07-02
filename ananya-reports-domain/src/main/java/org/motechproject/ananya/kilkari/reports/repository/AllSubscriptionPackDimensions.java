package org.motechproject.ananya.kilkari.reports.repository;

import org.motechproject.ananya.kilkari.reports.domain.dimension.OperatorDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.SubscriptionPackDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AllSubscriptionPackDimensions {
    @Autowired
    private DataAccessTemplate template;

    public AllSubscriptionPackDimensions() {
    }

    public SubscriptionPackDimension fetchFor(String subscriptionPack) {
        return (SubscriptionPackDimension) template.getUniqueResult(SubscriptionPackDimension.FIND_BY_PACK_NAME,
                new String[]{"subscriptionPack"}, new Object[]{subscriptionPack});
    }
}
