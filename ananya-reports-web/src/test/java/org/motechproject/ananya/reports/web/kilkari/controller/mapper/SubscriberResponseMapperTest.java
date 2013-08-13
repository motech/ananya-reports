package org.motechproject.ananya.reports.web.kilkari.controller.mapper;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.domain.SubscriptionStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.dimension.SubscriptionPackDimension;

import java.sql.Timestamp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class SubscriberResponseMapperTest {
    @Test
    public void shouldMapFromSubscriptionToSubscriberResponse() {
        DateTime edd = DateTime.now();
        DateTime dob = DateTime.now().minusYears(23);
        String subscriptionId = "subscriptionId";
        String name = "name";
        String district = "D1";
        String state = "S1";
        String block = "B1";
        String panchayat = "P1";
        SubscriptionStatus status = SubscriptionStatus.ACTIVE;
        String pack = "BARI_KILKARI";
        Long msisdn = 123L;
        Integer startWeekNumber = 45;
        Integer age = 23;
        DateTime lastScheduledDate = DateTime.now();
        DateTime subscriberModifiedTime = DateTime.now().plusDays(5);
        DateTime subscriptionModifiedTime = DateTime.now();
        Subscriber subscriber = new Subscriber(name, age, edd, dob, null, new LocationDimension(state, district, block, panchayat, "VALID"), null, null, startWeekNumber, subscriberModifiedTime);
        Subscription subscription = new Subscription(msisdn, subscriber, new SubscriptionPackDimension(pack), null, null, null, subscriptionId, subscriptionModifiedTime, DateTime.now(), status.name(), null);
        subscription.setLastScheduledMessageDate(new Timestamp(lastScheduledDate.getMillis()));
        LocationResponse expectedLocation = new LocationResponse(state, district, block, panchayat);

        SubscriberResponse subscriberResponse = SubscriberResponseMapper.mapFrom(subscription);

        assertEquals(subscriptionId, subscriberResponse.getSubscriptionId());
        assertEquals(name, subscriberResponse.getBeneficiaryName());
        assertEquals(age, subscriberResponse.getBeneficiaryAge());
        assertEquals(dob, subscriberResponse.getDateOfBirth());
        assertEquals(edd, subscriberResponse.getExpectedDateOfDelivery());
        assertEquals(expectedLocation, subscriberResponse.getLocationResponse());
        assertEquals(lastScheduledDate.getMillis(), subscriberResponse.getLastScheduledMessageDate().getMillis());
        assertEquals(subscriptionModifiedTime, subscriberResponse.getLastUpdatedTimeForSubscription());
        assertEquals(subscriberModifiedTime, subscriberResponse.getLastUpdatedTimeForBeneficiary());
    }

    @Test
    public void shouldHandleEmptyLocationDimension() {
        Subscriber subscriber = new Subscriber("name", 23, null, null, null, null, null, null, 22, DateTime.now());
        Subscription subscription = new Subscription(123L, subscriber, new SubscriptionPackDimension("BARI_KILKARI"), null, null, null, "subscriptionId", DateTime.now(), DateTime.now(), SubscriptionStatus.ACTIVE.name(), null);

        SubscriberResponse subscriberResponse = SubscriberResponseMapper.mapFrom(subscription);

        assertNull(subscriberResponse.getLocationResponse());
        assertNull(subscriberResponse.getLastScheduledMessageDate());
    }
}

