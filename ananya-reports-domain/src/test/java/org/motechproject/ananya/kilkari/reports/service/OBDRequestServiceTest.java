package org.motechproject.ananya.kilkari.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.OBDRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class OBDRequestServiceTest {
    private OBDRequestService obdRequestService;
    @Mock
    private SubscriberCallMeasureService subscriberCallMeasureService;

    @Before
    public void setUp() {
        initMocks(this);
        obdRequestService = new OBDRequestService(subscriberCallMeasureService);
    }
    @Test
    public void shouldCreateSubscriberCallDetails() {
        OBDRequest obdRequest = new OBDRequest();

        obdRequestService.createSubscriberCallDetails(obdRequest);

        verify(subscriberCallMeasureService).createSubscriberCallDetails(obdRequest);
    }
}
