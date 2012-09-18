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

    private ReportsPurgeService reportsPurgeService;

    @Before
    public void setUp() {
        reportsPurgeService = new ReportsPurgeService(subscriberCallMeasureService);
    }

    @Test
    public void shouldPurgeMeasuresBasedOnMsisdn() {
        Long msisdn = 1234L;

        reportsPurgeService.purge(msisdn);

        verify(subscriberCallMeasureService).deleteSubscriberCallDetailsFor(msisdn);
    }
}
