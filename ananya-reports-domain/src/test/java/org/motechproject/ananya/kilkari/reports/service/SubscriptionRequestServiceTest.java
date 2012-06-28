package org.motechproject.ananya.kilkari.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriptionRequestServiceTest {
    @Mock
    SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionRequestService subscriptionRequestService;

    @Before
    public void setUp(){
        initMocks(this);
        subscriptionRequestService = new SubscriptionRequestService(subscriptionStatusMeasureService);
    }

    @Test
    public void shouldCreateSubscription(){
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();

        subscriptionRequestService.createSubscription(subscriptionRequest);

        verify(subscriptionStatusMeasureService).createFor(subscriptionRequest);
    }

    @Test
    public void shouldUpdateSubscription() {
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest();

        subscriptionRequestService.updateSubscription(subscriptionStateChangeRequest);

        verify(subscriptionStatusMeasureService).update(subscriptionStateChangeRequest);
    }
}
