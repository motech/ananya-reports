package org.motechproject.ananya.reports.kilkari.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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
        Subscription subscription = mock(Subscription.class);

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
    public void shouldDeleteAllSubscriptionsForAGivenMsisdn() {
        Long msisdn = 1234L;

        subscriptionService.deleteFor(msisdn);

        verify(allSubscriptions).deleteFor(msisdn);
    }
}
