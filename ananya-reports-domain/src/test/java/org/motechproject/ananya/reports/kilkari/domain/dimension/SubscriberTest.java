package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SubscriberTest {
    @Test
    public void shouldUpdateSubscriberWithGivenDetails() {
        String state = "s1";
        String district = "d1";
        String block = "b1";
        String panchayat = "p1";
        SubscriberLocation location = new SubscriberLocation(state, district, block, panchayat);
        DateTime createdAt = DateTime.now();
        String beneficiaryName = "name";
        Integer beneficiaryAge = 24;
        Subscriber subscriber = new Subscriber("oldName", 23, DateTime.now().plus(42), DateTime.now().minusYears(3), null, new LocationDimension(state, "D1", "B1", "P1", "VALID"), null, null, null, DateTime.now());
        DateDimension expectedDateDimension = new DateDimension();
        LocationDimension expectedLocationDimension = new LocationDimension(state, district, block, panchayat, "VALID");

        subscriber.updateWith(new SubscriberReportRequest(createdAt, beneficiaryName, beneficiaryAge,
                location), expectedLocationDimension, expectedDateDimension);

        assertEquals(Integer.valueOf(beneficiaryAge), subscriber.getAgeOfBeneficiary());
        assertEquals(beneficiaryName, subscriber.getName());
        assertEquals(expectedDateDimension, subscriber.getDateDimension());
        assertEquals(expectedLocationDimension, subscriber.getLocationDimension());
        assertEquals(createdAt, new DateTime(subscriber.getLastModifiedTime()));
    }

    @Test
    public void shouldUpdateSubscriberDatesIfDobIsDifferent() {
        DateTime oldEdd = DateTime.now().plus(42);
        DateTime oldDob = DateTime.now().minusYears(3);
        DateTime newDob = DateTime.now().minusYears(1);
        Integer startWeekNumber = 33;
        DateTime oldLastModifiedTime = DateTime.now().minusDays(5);
        DateTime newLastModifiedTime = DateTime.now();
        Subscriber subscriber = new Subscriber("oldName", 23, oldEdd, oldDob, null, new LocationDimension("S1", "D1", "B1", "P1", "VALID"), null, null, startWeekNumber, oldLastModifiedTime);

        subscriber.updateSubscriptionDates(oldEdd, newDob, startWeekNumber, newLastModifiedTime);

        assertEquals(oldEdd, subscriber.getEstimatedDateOfDelivery());
        assertEquals(newDob, subscriber.getDateOfBirth());
        assertEquals(startWeekNumber, subscriber.getStartWeekNumber());
        assertEquals(newLastModifiedTime, new DateTime(subscriber.getLastModifiedTime()));
    }

    @Test
    public void shouldUpdateSubscriberDatesIfEddIsDifferentAndNull() {
        DateTime oldEdd = DateTime.now().plus(42);
        DateTime oldDob = DateTime.now().minusYears(3);
        Integer startWeekNumber = 33;
        DateTime oldLastModifiedTime = DateTime.now().minusDays(5);
        DateTime newLastModifiedTime = DateTime.now();
        Subscriber subscriber = new Subscriber("oldName", 23, oldEdd, oldDob, null, new LocationDimension("S1", "D1", "B1", "P1", "VALID"), null, null, startWeekNumber, oldLastModifiedTime);

        subscriber.updateSubscriptionDates(null, oldDob, startWeekNumber, newLastModifiedTime);

        assertNull(subscriber.getEstimatedDateOfDelivery());
        assertEquals(oldDob, subscriber.getDateOfBirth());
        assertEquals(startWeekNumber, subscriber.getStartWeekNumber());
        assertEquals(newLastModifiedTime, new DateTime(subscriber.getLastModifiedTime()));
    }

    @Test
    public void shouldUpdateSubscriberDatesIfStartWeekNumberIsDifferentAndNull() {
        DateTime oldEdd = DateTime.now().plus(42);
        DateTime oldDob = DateTime.now().minusYears(3);
        Integer oldStartWeekNumber = 38;
        Integer newStartWeekNumber = 64;
        DateTime oldLastModifiedTime = DateTime.now().minusDays(5);
        DateTime newLastModifiedTime = DateTime.now();
        Subscriber subscriber = new Subscriber("oldName", 23, oldEdd, oldDob, null, new LocationDimension("S1", "D1", "B1", "P1", "VALID"), null, null, oldStartWeekNumber, oldLastModifiedTime);

        subscriber.updateSubscriptionDates(oldEdd, oldDob, newStartWeekNumber, newLastModifiedTime);

        assertEquals(oldEdd, subscriber.getEstimatedDateOfDelivery());
        assertEquals(oldDob, subscriber.getDateOfBirth());
        assertEquals(newStartWeekNumber, subscriber.getStartWeekNumber());
        assertEquals(newLastModifiedTime, new DateTime(subscriber.getLastModifiedTime()));
    }

    @Test
    public void shouldNotUpdateSubscriberDateIfRequestIsNotDifferent() {
        DateTime edd = DateTime.now().plus(42);
        DateTime dob = DateTime.now().minusYears(3);
        Integer startWeekNumber = 33;
        DateTime lastModifiedTime = DateTime.now().minusDays(5);
        Subscriber subscriber = new Subscriber("oldName", 23, edd, dob, null, new LocationDimension("S1", "D1", "B1", "P1", "VALID"), null, null, startWeekNumber, lastModifiedTime);

        subscriber.updateSubscriptionDates(edd, dob, startWeekNumber, lastModifiedTime);

        assertEquals(edd, subscriber.getEstimatedDateOfDelivery());
        assertEquals(dob, subscriber.getDateOfBirth());
        assertEquals(startWeekNumber, subscriber.getStartWeekNumber());
        assertEquals(lastModifiedTime, new DateTime(subscriber.getLastModifiedTime()));
    }
}
