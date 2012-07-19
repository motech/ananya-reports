package org.motechproject.ananya.kilkari.reports.web.mapper;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.web.response.SubscriptionResponse;

import static junit.framework.Assert.assertEquals;

public class SubscriptionStatusMeasureMapperTest {
    @Test
    public void shouldMapFromSubscriptionStatusMeasureToResponse() {
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String subscriptionId = "subscriptionId";
        String name = "name";
        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        String status = "ACTIVE";
        String pack = "FIFTEEN_MONTHS";
        Subscriber subscriber = new Subscriber(12349L, name, 23, edd, dob, null, new LocationDimension(district, block, panchayat), null, null);
        Subscription subscription = new Subscription(subscriber, null, null, null, null, null, subscriptionId);

        SubscriptionResponse subscriptionResponse = SubscriptionStatusMeasureMapper.mapFrom(new SubscriptionStatusMeasure(subscription, status, 12, null, null, null, null, new SubscriptionPackDimension(pack), null, null));

        assertEquals(subscriptionId, subscriptionResponse.getSubscriptionId());
        assertEquals(name, subscriptionResponse.getName());
        assertEquals(status, subscriptionResponse.getSubscriptionStatus());
        assertEquals(pack, subscriptionResponse.getSubscriptionPack());
        assertEquals(edd.toString(), subscriptionResponse.getEstimatedDateOfDelivery());
        assertEquals(dob.toString(), subscriptionResponse.getDateOfBirth());
        assertEquals(district, subscriptionResponse.getLocationResponse().getDistrict());
        assertEquals(block, subscriptionResponse.getLocationResponse().getBlock());
        assertEquals(panchayat, subscriptionResponse.getLocationResponse().getPanchayat());
    }
}
