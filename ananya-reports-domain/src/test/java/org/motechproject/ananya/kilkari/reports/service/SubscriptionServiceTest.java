package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriptions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        Subscription subscription = new Subscription(subscriber, subscriptionPackDimension, channelDimension, operatorDimension, locationDimension, dateDimension, subscriptionId, DateTime.now(), "ACTIVE", 13);

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
}
