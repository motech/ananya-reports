package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriptionStatusMeasureServiceTest {
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    @Mock
    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasure;
    @Mock
    private AllChannelDimensions allChannelDimensions;
    @Mock
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    @Mock
    private AllOperatorDimensions allOperatorDimensions;
    @Mock
    private AllSubscribers allSubscribers;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private AllSubscriptions allSubscriptions;
    @Mock
    private AllDateDimensions allTimeDimension;
    @Mock
    private AllLocationDimensions allLocationDimensions;

    @Before
    public void setup() {
        initMocks(this);
        subscriptionStatusMeasureService = new SubscriptionStatusMeasureService(allSubscriptionStatusMeasure,
                allChannelDimensions, allSubscriptionPackDimensions, allOperatorDimensions, allSubscribers,
                subscriptionService, allSubscriptions, allTimeDimension, allLocationDimensions);

    }

    @Test
    public void shouldCreateSubscriptionStatusMeasure() {
        long msisdn = 998L;
        String channel = "IVR";
        String name = "name";
        int age = 42;
        String subscriptionId = "sub112";
        String operator = "airtel";
        String subscriptionPack = "TWELVE_MONTHS";
        DateTime edd = DateTime.now().minusMonths(4);
        DateTime dob = DateTime.now().minusMonths(8);
        String district = "district";
        String block = "block";
        String panchayat = "panchayat";

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setMsisdn(msisdn);
        subscriptionRequest.setChannel(channel);
        subscriptionRequest.setSubscriptionId(subscriptionId);
        subscriptionRequest.setOperator(operator);
        subscriptionRequest.setPack(subscriptionPack);
        subscriptionRequest.setCreatedAt(new DateTime(2012, 01, 01, 10, 10));
        subscriptionRequest.setEstimatedDateOfDelivery(edd);
        subscriptionRequest.setDateOfBirth(dob);
        subscriptionRequest.setName(name);
        subscriptionRequest.setAgeOfBeneficiary(age);
        subscriptionRequest.setLocation(new SubscriberLocation(district, block, panchayat));
        subscriptionRequest.setSubscriptionStatus("subscriptionstatus");

        ChannelDimension channelDimension = new ChannelDimension();
        DateDimension dateDimension = new DateDimension();
        LocationDimension locationDimension = new LocationDimension();

        Subscriber subscriber = new Subscriber();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension(subscriptionPack);
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(subscriptionId);

        when(subscriptionService.exists(subscriptionId)).thenReturn(false);
        when(allChannelDimensions.fetchFor(channel)).thenReturn(channelDimension);
        when(allSubscriptionPackDimensions.fetchFor(subscriptionPack)).thenReturn(subscriptionPackDimension);
        when(allTimeDimension.fetchFor(new DateTime(subscriptionRequest.getCreatedAt()))).thenReturn(dateDimension);
        when(allLocationDimensions.fetchFor(district, block, panchayat)).thenReturn(locationDimension);
        when(allSubscribers.save(msisdn, name, age, edd, dob, channelDimension, locationDimension,
                dateDimension, null)).thenReturn(subscriber);
        when(subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension,
                null, locationDimension, dateDimension, subscriptionId)).thenReturn(subscription);

        subscriptionStatusMeasureService.createFor(subscriptionRequest);

        ArgumentCaptor<SubscriptionStatusMeasure> captor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(captor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = captor.getValue();
        assertEquals(subscriptionId, subscriptionStatusMeasure.getSubscription().getSubscriptionId());
        assertEquals(dateDimension, subscriptionStatusMeasure.getDateDimension());
        assertEquals(13, subscriptionStatusMeasure.getWeekNumber());
    }

    @Test
    public void shouldUpdateSubscriptionStatusMeasureOnSubscriptionStateChange() {
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest();
        String subscriptionId = "sub123";
        String subscriptionStatus = "ACTIVE";
        String reason = "my own reason";
        String operator = "airtel";
        Integer graceCount = 4;
        DateTime createdAt = new DateTime(2012, 02, 01, 10, 10);
        subscriptionStateChangeRequest.setSubscriptionId(subscriptionId);
        subscriptionStateChangeRequest.setSubscriptionStatus(subscriptionStatus);
        subscriptionStateChangeRequest.setCreatedAt(createdAt);
        subscriptionStateChangeRequest.setReason(reason);
        subscriptionStateChangeRequest.setOperator(operator);
        subscriptionStateChangeRequest.setGraceCount(graceCount);

        ChannelDimension channelDimension = new ChannelDimension();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("TWELVE_MONTHS");
        DateDimension mockedDateDimension = new DateDimension(new DateTime(2012, 01, 01, 10, 10));

        Subscription mockedSubscription = mock(Subscription.class);
        when(mockedSubscription.getChannelDimension()).thenReturn(channelDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(subscriptionPackDimension);
        when(mockedSubscription.getDateDimension()).thenReturn(mockedDateDimension);
        when(mockedSubscription.getSubscriptionId()).thenReturn(subscriptionId);
        Subscriber mockedSubscriber = mock(Subscriber.class);
        when(mockedSubscription.getSubscriber()).thenReturn(mockedSubscriber);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        DateDimension dateDimension = new DateDimension(createdAt);
        when(allTimeDimension.fetchFor(any(DateTime.class))).thenReturn(dateDimension);
        OperatorDimension operatorDimension = new OperatorDimension(operator);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);

        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);

        verify(allSubscriptions).update(mockedSubscription);

        ArgumentCaptor<SubscriptionStatusMeasure> subscriptionStatusMeasureArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(subscriptionStatusMeasureArgumentCaptor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = subscriptionStatusMeasureArgumentCaptor.getValue();
        Subscription subscription = subscriptionStatusMeasure.getSubscription();

        assertEquals(subscriptionStatus, subscriptionStatusMeasure.getStatus());
        assertEquals(17, subscriptionStatusMeasure.getWeekNumber());
        assertEquals(channelDimension, subscriptionStatusMeasure.getChannelDimension());
        assertEquals(operatorDimension, subscriptionStatusMeasure.getOperatorDimension());
        assertEquals(reason, subscriptionStatusMeasure.getRemarks());
        assertEquals(subscriptionPackDimension, subscriptionStatusMeasure.getSubscriptionPackDimension());
        assertEquals(createdAt, new DateTime(subscriptionStatusMeasure.getDateDimension().getDate().getTime()));
        assertEquals(subscriptionId, subscription.getSubscriptionId());
        assertEquals(graceCount, subscriptionStatusMeasure.getGraceCount());
    }

    @Test
    public void shouldRetainOperatorIfOperatorInRequestIsNullDuringUpdate() {
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest();
        String subscriptionId = "sub123";
        String subscriptionStatus = "ACTIVE";
        String reason = "my own reason";
        String operator = "airtel";
        Integer graceCount = 4;
        DateTime createdAt = new DateTime(2012, 02, 01, 10, 10);
        subscriptionStateChangeRequest.setSubscriptionId(subscriptionId);
        subscriptionStateChangeRequest.setSubscriptionStatus(subscriptionStatus);
        subscriptionStateChangeRequest.setCreatedAt(createdAt);
        subscriptionStateChangeRequest.setReason(reason);
        subscriptionStateChangeRequest.setOperator(null);
        subscriptionStateChangeRequest.setGraceCount(graceCount);

        ChannelDimension channelDimension = new ChannelDimension();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("TWELVE_MONTHS");
        DateDimension mockedDateDimension = new DateDimension(new DateTime(2012, 01, 01, 10, 10));
        OperatorDimension operatorDimension = new OperatorDimension(operator);

        Subscription mockedSubscription = mock(Subscription.class);
        when(mockedSubscription.getChannelDimension()).thenReturn(channelDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(subscriptionPackDimension);
        when(mockedSubscription.getDateDimension()).thenReturn(mockedDateDimension);
        when(mockedSubscription.getOperatorDimension()).thenReturn(operatorDimension);
        when(mockedSubscription.getSubscriptionId()).thenReturn(subscriptionId);
        Subscriber mockedSubscriber = mock(Subscriber.class);
        when(mockedSubscription.getSubscriber()).thenReturn(mockedSubscriber);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        DateDimension dateDimension = new DateDimension(createdAt);
        when(allTimeDimension.fetchFor(any(DateTime.class))).thenReturn(dateDimension);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);

        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);

        verify(mockedSubscriber).setOperatorDimension(operatorDimension);
        verify(mockedSubscription).setOperatorDimension(operatorDimension);

        ArgumentCaptor<SubscriptionStatusMeasure> subscriptionStatusMeasureArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(subscriptionStatusMeasureArgumentCaptor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = subscriptionStatusMeasureArgumentCaptor.getValue();

        assertEquals(operatorDimension, subscriptionStatusMeasure.getOperatorDimension());
    }
}
