package org.motechproject.ananya.kilkari.reports.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.OperatorDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.SubscriptionPackDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllSubscriptionPackDimensionsIT extends SpringIntegrationTest{
    @Autowired
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;

    @After
    @Before
    public void After() {
        template.deleteAll(template.loadAll(SubscriptionPackDimension.class));
    }

    @Test
    public void shouldFetchByPackName() {
        String subscriptionPack = "PCK1";
        template.save(new SubscriptionPackDimension(subscriptionPack));
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor(subscriptionPack);
        assertEquals(subscriptionPack,subscriptionPackDimension.getSubscriptionPack());
    }

}
