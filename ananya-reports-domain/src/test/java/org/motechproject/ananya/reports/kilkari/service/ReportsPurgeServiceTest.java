package org.motechproject.ananya.reports.kilkari.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportsPurgeServiceTest {
    @Mock
    private SubscriberCallMeasureService subscriberCallMeasureService;
    @Mock
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    @Mock
    private SubscriptionService subscriptionService;

    private ReportsPurgeService reportsPurgeService;

    @Before
    public void setUp() {
        reportsPurgeService = new ReportsPurgeService(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
    }

    @Test
    public void shouldPurgeSubscriberCallMeasuresBasedOnMsisdn() {
        String msisdn = "1234";

        reportsPurgeService.purge(msisdn);

        verify(subscriberCallMeasureService).deleteFor(Long.valueOf(msisdn));
    }

    @Test
    public void shouldPurgeSubscriptionStatusMeasuresBasedOnMsisdn() {
        String msisdn = "1234";

        reportsPurgeService.purge(msisdn);

        verify(subscriptionStatusMeasureService).deleteFor(Long.valueOf(msisdn));
    }

    @Test
    public void shouldPurgeSubscriptionsBasedOnMsisdn() {
        String msisdn = "1234";

        reportsPurgeService.purge(msisdn);

        verify(subscriptionService).deleteFor(Long.valueOf(msisdn));
    }

    @Test
    public void shouldDeleteMeasuresBeforeDeletingTheDynamicDimensions() {
        String msisdn = "1234";
        Long msisdnAsLong = Long.valueOf(msisdn);

        reportsPurgeService.purge(msisdn);

        InOrder order = inOrder(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
        order.verify(subscriberCallMeasureService).deleteFor(msisdnAsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdnAsLong);
        order.verify(subscriptionService).deleteFor(msisdnAsLong);
    }
}
