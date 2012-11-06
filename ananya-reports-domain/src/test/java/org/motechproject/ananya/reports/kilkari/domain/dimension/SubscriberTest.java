package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;

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
        Subscriber subscriber = new Subscriber("oldName", 23, DateTime.now().plus(42), DateTime.now().minusYears(3), null, new LocationDimension("D1", "B1", "P1", "VALID", null), null, null);
        DateDimension expectedDateDimension = new DateDimension();
        LocationDimension expectedLocationDimension = new LocationDimension(district, block, panchayat, "VALID", null);

        subscriber.updateWith(new SubscriberReportRequest(createdAt, beneficiaryName, beneficiaryAge,
                location), expectedLocationDimension, expectedDateDimension);

        assertEquals(Integer.valueOf(beneficiaryAge), subscriber.getAgeOfBeneficiary());
        assertEquals(beneficiaryName, subscriber.getName());
        assertEquals(expectedDateDimension, subscriber.getDateDimension());
        assertEquals(expectedLocationDimension, subscriber.getLocationDimension());
    }

    @Test
    public void shouldUpdateSubscriberWithEddDob() {
        DateTime oldEdd = DateTime.now().plus(42);
        DateTime oldDob = DateTime.now().minusYears(3);
        DateTime newEdd = DateTime.now().plus(32);
        DateTime newDob = DateTime.now().minusYears(1);
        Subscriber subscriber = new Subscriber("oldName", 23, oldEdd, oldDob, null, new LocationDimension("D1", "B1", "P1", "VALID", null), null, null);

        subscriber.updateWithEddDob(newEdd, newDob);

        assertEquals(newEdd, subscriber.getEstimatedDateOfDelivery());
        assertEquals(newDob, subscriber.getDateOfBirth());
    }
}
