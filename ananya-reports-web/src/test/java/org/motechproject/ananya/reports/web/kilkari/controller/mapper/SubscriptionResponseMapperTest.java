package org.motechproject.ananya.reports.web.kilkari.controller.mapper;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriptionResponse;
import org.motechproject.ananya.reports.kilkari.domain.SubscriptionStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.dimension.SubscriptionPackDimension;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class SubscriptionResponseMapperTest {
    @Test
    public void shouldMapFromSubscriptionStatusMeasureToResponse() {
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String expectedEdd = edd.toString("dd-MM-yyyy");
        String expectedDob = dob.toString("dd-MM-yyyy");
        String subscriptionId = "subscriptionId";
        String name = "name";
        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        SubscriptionStatus status = SubscriptionStatus.ACTIVE;
        String pack = "BARI_KILKARI";
        Long msisdn = 123L;
        Subscriber subscriber = new Subscriber(name, 23, edd, dob, null, new LocationDimension(district, block, panchayat), null, null);
        Subscription subscription = new Subscription(msisdn, subscriber, new SubscriptionPackDimension(pack), null, null, null, subscriptionId, DateTime.now(), DateTime.now(), status.name(), 13, null);

        SubscriptionResponse subscriptionResponse = SubscriptionResponseMapper.mapFrom(subscription);

        assertEquals(subscriptionId, subscriptionResponse.getSubscriptionId());
        assertEquals(msisdn, subscriptionResponse.getMsisdn());
        assertEquals(name, subscriptionResponse.getBeneficiaryName());
        assertEquals(status.getDisplayString(), subscriptionResponse.getSubscriptionStatus());
        assertEquals(pack, subscriptionResponse.getPack());
        assertEquals(expectedEdd, subscriptionResponse.getExpectedDateOfDelivery());
        assertEquals(expectedDob, subscriptionResponse.getDateOfBirth());
        assertEquals(district, subscriptionResponse.getLocation().getDistrict());
        assertEquals(block, subscriptionResponse.getLocation().getBlock());
        assertEquals(panchayat, subscriptionResponse.getLocation().getPanchayat());
    }

    @Test
    public void shouldHandleEmptyLocationDimension() {
        String subscriptionId = "subscriptionId";
        String name = "name";
        SubscriptionStatus status = SubscriptionStatus.ACTIVE;
        String pack = "BARI_KILKARI";
        Subscriber subscriber = new Subscriber(name, 23, null, null, null, null, null, null);
        Long msisdn = 123L;
        Subscription subscription = new Subscription(msisdn, subscriber, new SubscriptionPackDimension(pack), null, null, null, subscriptionId, DateTime.now(), DateTime.now(), status.name(), 13, null);

        SubscriptionResponse subscriptionResponse = SubscriptionResponseMapper.mapFrom(subscription);

        assertEquals(subscriptionId, subscriptionResponse.getSubscriptionId());
        assertEquals(msisdn, subscriptionResponse.getMsisdn());
        assertEquals(name, subscriptionResponse.getBeneficiaryName());
        assertEquals(status.getDisplayString(), subscriptionResponse.getSubscriptionStatus());
        assertEquals(pack, subscriptionResponse.getPack());
        assertNull(subscriptionResponse.getExpectedDateOfDelivery());
        assertNull(subscriptionResponse.getDateOfBirth());
        assertNull(subscriptionResponse.getLocation());
    }
}
