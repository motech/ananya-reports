package org.motechproject.ananya.kilkari.reports.domain.dimension;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriberRequest;

import static org.junit.Assert.assertEquals;

public class SubscriberTest {
    @Test
    public void shouldUpdateSubscriberWithGivenDetails() {
        String district = "d1";
        String block = "b1";
        String panchayat = "p1";
        SubscriberLocation location = new SubscriberLocation(district, block, panchayat);
        long msisdn = 1234567890L;
        DateTime createdAt = DateTime.now();
        String beneficiaryName = "name";
        Integer beneficiaryAge = 24;
        DateTime expectedDateOfDelivery = DateTime.now().plusMonths(1);
        DateTime dateOfBirth = DateTime.now().minusYears(10);
        Subscriber subscriber = new Subscriber(msisdn, "oldName", 23, DateTime.now().plus(42), DateTime.now().minusYears(3), null, new LocationDimension("D1", "B1", "P1"), null, null);
        DateDimension expectedDateDimension = new DateDimension();
        LocationDimension expectedLocationDimension = new LocationDimension(district, block, panchayat);

        subscriber.updateWith(new SubscriberRequest(createdAt, beneficiaryName, beneficiaryAge,
                expectedDateOfDelivery, dateOfBirth, location), expectedLocationDimension, expectedDateDimension);

        assertEquals(Integer.valueOf(beneficiaryAge), subscriber.getAgeOfBeneficiary());
        assertEquals(beneficiaryName, subscriber.getName());
        assertEquals(dateOfBirth, subscriber.getDateOfBirth());
        assertEquals(expectedDateOfDelivery, subscriber.getEstimatedDateOfDelivery());
        assertEquals(expectedDateDimension, subscriber.getDateDimension());
        assertEquals(expectedLocationDimension, subscriber.getLocationDimension());
    }
}