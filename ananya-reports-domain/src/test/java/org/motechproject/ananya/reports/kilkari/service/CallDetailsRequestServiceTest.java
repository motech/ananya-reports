package org.motechproject.ananya.reports.kilkari.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallDetailsRequestServiceTest {
    private CallDetailsRequestService callDetailsRequestService;
    @Mock
    private SubscriberCallMeasureService subscriberCallMeasureService;

    @Before
    public void setUp() {
        initMocks(this);
        callDetailsRequestService = new CallDetailsRequestService(subscriberCallMeasureService);
    }
    @Test
    public void shouldCreateSubscriberCallDetails() {
        CallDetailsReportRequest callDetailsReportRequest = new CallDetailsReportRequest();

        callDetailsRequestService.createSubscriberCallDetails(callDetailsReportRequest);

        verify(subscriberCallMeasureService).createSubscriberCallDetails(callDetailsReportRequest);
    }
}
