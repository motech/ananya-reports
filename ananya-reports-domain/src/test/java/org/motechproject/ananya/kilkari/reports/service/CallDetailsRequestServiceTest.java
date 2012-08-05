package org.motechproject.ananya.kilkari.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.contract.request.CallDetailsRequest;

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
        CallDetailsRequest callDetailsRequest = new CallDetailsRequest();

        callDetailsRequestService.createSubscriberCallDetails(callDetailsRequest);

        verify(subscriberCallMeasureService).createSubscriberCallDetails(callDetailsRequest);
    }
}
