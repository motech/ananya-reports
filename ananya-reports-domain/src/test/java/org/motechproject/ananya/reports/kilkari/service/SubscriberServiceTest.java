package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscribers;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;

import java.util.ArrayList;
import java.util.List;

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
    private AllLocationDimensions allLocationDimensions;
    @Mock
    private AllDateDimensions allDateDimensions;
    @Captor
    private ArgumentCaptor<List<Subscriber>> subscribersCaptor;

    @Before
    public void setUp() {
        initMocks(this);
        subscriberService = new SubscriberService(allSubscriptions, allSubscribers, allLocationDimensions, allDateDimensions);
    }

    @Test
    public void shouldUpdateSubscriberDetails() {
        String district = "d1";
        String block = "b1";
        String panchayat = "p1";
        SubscriberLocation location = new SubscriberLocation(district, block, panchayat);
        String subscriptionId = "abcd1234";
        DateTime createdAt = DateTime.now();
        String beneficiaryName = "name";
        Integer beneficiaryAge = 24;
        Subscriber subscriber = new Subscriber("oldName", 23, DateTime.now().plus(42), DateTime.now().minusYears(3), null, new LocationDimension("D2", "B2", "P2", "VALID", null), null, null);
        DateDimension expectedDateDimension = new DateDimension();
        Subscription subscription = mock(Subscription.class);

        when(subscription.getSubscriber()).thenReturn(subscriber);
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(subscription);
        when(allLocationDimensions.fetchFor(district, block, panchayat)).thenReturn(new LocationDimension(district, block, panchayat, "VALID", null));
        when(allDateDimensions.fetchFor(createdAt)).thenReturn(expectedDateDimension);

        subscriberService.update(new SubscriberReportRequest(createdAt, beneficiaryName, beneficiaryAge, location), subscriptionId);

        ArgumentCaptor<Subscriber> captor = ArgumentCaptor.forClass(Subscriber.class);
        verify(allSubscribers).save(captor.capture());
        Subscriber actualSubscriber = captor.getValue();

        assertEquals(Integer.valueOf(beneficiaryAge), actualSubscriber.getAgeOfBeneficiary());
        assertEquals(beneficiaryName, actualSubscriber.getName());
        assertEquals(district, actualSubscriber.getLocationDimension().getDistrict());
        assertEquals(block, actualSubscriber.getLocationDimension().getBlock());
        assertEquals(panchayat, actualSubscriber.getLocationDimension().getPanchayat());
        assertEquals(expectedDateDimension, actualSubscriber.getDateDimension());
    }

    @Test
    public void shouldUpdateLocation() {
        LocationDimension oldLocation = new LocationDimension("D1", "B1", "P1", LocationStatus.NOT_VERIFIED.name(), null);
        LocationDimension newLocation = new LocationDimension("D1", "B1", "P1", LocationStatus.VALID.name(), null);
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new Subscriber("name", null, null, null, null, oldLocation, null, null));
        when(allSubscribers.findByLocation(oldLocation)).thenReturn(subscribers);

        subscriberService.updateLocation(oldLocation, newLocation);

        verify(allSubscribers).saveOrUpdateAll(subscribersCaptor.capture());
        List<Subscriber> actualSubscribersList = subscribersCaptor.getValue();
        assertEquals(1, actualSubscribersList.size());
        assertEquals(newLocation, actualSubscribersList.get(0).getLocationDimension());
    }
}
