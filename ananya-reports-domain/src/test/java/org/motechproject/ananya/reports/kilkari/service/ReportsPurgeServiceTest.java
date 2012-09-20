package org.motechproject.ananya.reports.kilkari.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportsPurgeServiceTest {
    @Mock
    private SubscriberCallMeasureService subscriberCallMeasureService;
    @Mock
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;

    private ReportsPurgeService reportsPurgeService;

    @Before
    public void setUp() {
        reportsPurgeService = new ReportsPurgeService(subscriberCallMeasureService, subscriptionStatusMeasureService);
    }

    @Test
    public void shouldPurgeSubscriberCallMeasuresBasedOnMsisdn() {
        Long msisdn = 1234L;

        reportsPurgeService.purge(msisdn);

        verify(subscriberCallMeasureService).deleteFor(msisdn);
    }

    @Test
    public void shouldPurgeSubscriptionStatusMeasuresBasedOnMsisdn() {
        Long msisdn = 1234L;

        reportsPurgeService.purge(msisdn);

        verify(subscriptionStatusMeasureService).deleteFor(msisdn);
    }
}
