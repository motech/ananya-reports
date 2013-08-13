package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscribers;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriberServiceTest {
    private SubscriberService subscriberService;
    @Mock
    private AllSubscriptions allSubscriptions;
    @Mock
    private AllSubscribers allSubscribers;
    @Mock
    private LocationService locationService;
    @Mock
    private AllDateDimensions allDateDimensions;
    @Captor
    private ArgumentCaptor<List<Subscriber>> subscribersCaptor;

    @Before
    public void setUp() {
        initMocks(this);
        subscriberService = new SubscriberService(allSubscriptions, allSubscribers, locationService, allDateDimensions);
    }

    @Test
    public void shouldUpdateSubscriberDetails() {
        String state = "S1";
        String district = "D1";
        String block = "B1";
        String panchayat = "P1";
        SubscriberLocation location = new SubscriberLocation(state, district, block, panchayat);
        String subscriptionId = "abcd1234";
        DateTime createdAt = DateTime.now();
        String beneficiaryName = "name";
        Integer beneficiaryAge = 24;
        Subscriber subscriber = new Subscriber("oldName", 23, DateTime.now().plus(42), DateTime.now().minusYears(3), null, new LocationDimension(state, "D2", "B2", "P2", "VALID"), null, null, null, createdAt.minusDays(5));
        DateDimension expectedDateDimension = new DateDimension();
        Subscription subscription = mock(Subscription.class);

        when(subscription.getSubscriber()).thenReturn(subscriber);
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(subscription);
        when(locationService.createAndFetch(location)).thenReturn(new LocationDimension(state, district, block, panchayat, "VALID"));
        when(allDateDimensions.fetchFor(createdAt)).thenReturn(expectedDateDimension);

        subscriberService.update(new SubscriberReportRequest(createdAt, beneficiaryName, beneficiaryAge, location), subscriptionId);

        verify(locationService).createAndFetch(location);
        ArgumentCaptor<Subscriber> captor = ArgumentCaptor.forClass(Subscriber.class);
        verify(allSubscribers).save(captor.capture());
        Subscriber actualSubscriber = captor.getValue();

        assertEquals(Integer.valueOf(beneficiaryAge), actualSubscriber.getAgeOfBeneficiary());
        assertEquals(beneficiaryName, actualSubscriber.getName());
        assertEquals(district, actualSubscriber.getLocationDimension().getDistrict());
        assertEquals(block, actualSubscriber.getLocationDimension().getBlock());
        assertEquals(panchayat, actualSubscriber.getLocationDimension().getPanchayat());
        assertEquals(expectedDateDimension, actualSubscriber.getDateDimension());
        assertEquals(createdAt, new DateTime(actualSubscriber.getLastModifiedTime()));
    }

    @Test
    public void shouldDeleteSubscribers() {
        Subscriber subscriber1 = new Subscriber();
        Set<Subscription> subscriptions = new HashSet<>();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("BARI_KILKARI");
        Subscription subscription1 = new Subscription(1L, subscriber1, subscriptionPackDimension, null, null, null, null, DateTime.now(), DateTime.now(), "NEW", null);
        subscriptions.add(subscription1);
        Subscription subscription2 = new Subscription(1L, subscriber1, subscriptionPackDimension, null, null, null, null, DateTime.now(), DateTime.now(), "NEW", null);
        subscriptions.add(subscription2);
        HashSet<Subscriber> expectedSubscribers = new HashSet<>();
        expectedSubscribers.add(subscriber1);

        subscriberService.deleteFor(subscriptions);

        verify(allSubscribers).delete(expectedSubscribers);
    }
}
