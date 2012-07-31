package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriberRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.repository.AllLocationDimensions;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscribers;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriptions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriberServiceTest {
    private SubscriberService subscriberService;
    @Mock
    private AllSubscriptions allSubscriptions;
    @Mock
    private AllSubscribers allSubscribers;
    @Mock
    private AllLocationDimensions allLocationDimensions;

    @Before
    public void setUp() {
        initMocks(this);
        subscriberService = new SubscriberService(allSubscriptions, allSubscribers, allLocationDimensions);
    }

    @Test
    public void shouldUpdateSubscriberDetails() {
        String district = "d1";
        String block = "b1";
        String panchayat = "p1";
        SubscriberLocation location = new SubscriberLocation(district, block, panchayat);
        String subscriptionId = "abcd1234";
        long msisdn = 1234567890L;
        DateTime createdAt = DateTime.now();
        String beneficiaryName = "name";
        int beneficiaryAge = 24;
        DateTime expectedDateOfDelivery = DateTime.now().plusMonths(1);
        DateTime dateOfBirth = DateTime.now().minusYears(10);
        Subscriber subscriber = new Subscriber(msisdn, "oldName", 23, DateTime.now().plus(42), DateTime.now().minusYears(3), null, new LocationDimension("D2", "B2", "P2"), null, null);
        Subscription subscription = mock(Subscription.class);

        when(subscription.getSubscriber()).thenReturn(subscriber);
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(subscription);
        when(allLocationDimensions.fetchFor(district, block, panchayat)).thenReturn(new LocationDimension(district, block, panchayat));

        subscriberService.update(new SubscriberRequest(subscriptionId, createdAt, beneficiaryName, beneficiaryAge, expectedDateOfDelivery, dateOfBirth, location));

        ArgumentCaptor<Subscriber> captor = ArgumentCaptor.forClass(Subscriber.class);
        verify(allSubscribers).save(captor.capture());
        Subscriber actualSubscriber = captor.getValue();

        assertEquals(beneficiaryAge, actualSubscriber.getAgeOfBeneficiary());
        assertEquals(beneficiaryName, actualSubscriber.getName());
        assertEquals(expectedDateOfDelivery, actualSubscriber.getEstimatedDateOfDelivery());
        assertEquals(dateOfBirth, actualSubscriber.getDateOfBirth());
        assertEquals(district, actualSubscriber.getLocationDimension().getDistrict());
        assertEquals(block, actualSubscriber.getLocationDimension().getBlock());
        assertEquals(panchayat, actualSubscriber.getLocationDimension().getPanchayat());
    }
}
