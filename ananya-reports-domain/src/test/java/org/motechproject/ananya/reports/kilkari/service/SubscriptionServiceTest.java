package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriptionServiceTest {
    @Mock
    private AllSubscriptions allSubscriptions;
    private SubscriptionService subscriptionService;

    @Before
    public void setup() {
        initMocks(this);
        subscriptionService = new SubscriptionService(allSubscriptions);
    }

    @Test
    public void shouldReturnTrueIfSubscriptionAlreadyExists() throws Exception {
        String subscriptionId = "sub11";
        Subscription subscription = new Subscription();
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(subscription);

        assertTrue(subscriptionService.exists(subscriptionId));
    }

    @Test
    public void shouldReturnFalseIfSubscriptionDoesNotExists() throws Exception {
        String subscriptionId = "sub11";
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(null);

        assertFalse(subscriptionService.exists(subscriptionId));
    }

    @Test
    public void shouldMakeASubscription() {
        Subscriber subscriber = new Subscriber();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension();
        ChannelDimension channelDimension = new ChannelDimension();
        DateDimension dateDimension = new DateDimension();
        OperatorDimension operatorDimension = new OperatorDimension();
        LocationDimension locationDimension = new LocationDimension();
        String subscriptionId = "sub11";
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, operatorDimension, locationDimension, dateDimension, subscriptionId, DateTime.now(), DateTime.now(), "ACTIVE", 13, null);

        subscriptionService.makeFor(subscription);

        verify(allSubscriptions).save(subscription);
    }

    @Test
    public void shouldFetchASubscriptionByID() {
        String subscriptionId = "abcd1234";

        subscriptionService.fetchFor(subscriptionId);

        verify(allSubscriptions).findBySubscriptionId(subscriptionId);
    }

    @Test
    public void shouldFindSubscriptionsByMsisdn() {
        Long msisdn = 1234567890L;
        ArrayList<Subscription> expectedSubscriptionList = new ArrayList<>();
        when(allSubscriptions.findByMsisdn(msisdn)).thenReturn(expectedSubscriptionList);

        List<Subscription> actualSubscriptionList = subscriptionService.findByMsisdn(msisdn.toString());

        assertEquals(expectedSubscriptionList, actualSubscriptionList);
    }

    @Test
    public void shouldChangeMsisdnForSubscription() {
        String subscriptionId = "subscriptionId";
        Subscription subscription = new Subscription();
        subscription.setSubscriber(new Subscriber());
        long msisdn = 9876543210L;

        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(subscription);

        subscriptionService.changeMsisdnForSubscription(subscriptionId, msisdn);

        ArgumentCaptor<Subscription> subscriptionArgumentCaptor = ArgumentCaptor.forClass(Subscription.class);
        verify(allSubscriptions).update(subscriptionArgumentCaptor.capture());
        Subscription modifiedSubscription = subscriptionArgumentCaptor.getValue();

        assertEquals(msisdn, (long)modifiedSubscription.getSubscriber().getMsisdn());
    }
}
