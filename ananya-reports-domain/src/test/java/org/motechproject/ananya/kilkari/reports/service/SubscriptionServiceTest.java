package org.motechproject.ananya.kilkari.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.repository.AllSubscriptions;

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
    public void setup(){
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
    public void shouldMakeASubscription(){
        Subscriber subscriber = new Subscriber();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension();
        ChannelDimension channelDimension = new ChannelDimension();
        TimeDimension timeDimension = new TimeDimension();
        OperatorDimension operatorDimension = new OperatorDimension();
        LocationDimension locationDimension = new LocationDimension();
        String subscriptionId = "sub11";
        subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, locationDimension, timeDimension, subscriptionId);
        
        verify(allSubscriptions).save(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, locationDimension, timeDimension, subscriptionId);
        
    }

    @Test
    public void shouldFetchASubscriptionByID() {
        String subscriptionId = "abcd1234";

        subscriptionService.fetchFor(subscriptionId);

        verify(allSubscriptions).findBySubscriptionId(subscriptionId);
    }
}
