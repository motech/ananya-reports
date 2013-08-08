package org.motechproject.ananya.reports.web.kilkari.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.motechproject.ananya.reports.kilkari.service.CallDetailsRequestService;
import org.motechproject.ananya.reports.web.kilkari.controller.CallDetailsController;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallDetailsControllerTest {

    private CallDetailsController callDetailsController;
    @Mock
    private CallDetailsRequestService callDetailsRequestService;

    @Before
    public void setUp(){
        initMocks(this);
        callDetailsController = new CallDetailsController(callDetailsRequestService);
    }

    @Test
    public void shouldCreateSubscriberCallDetails() throws Exception {
        CallDetailsReportRequest callDetailsReportRequest = new CallDetailsReportRequest();

        callDetailsController.createSubscriberCallDetails(callDetailsReportRequest);

        verify(callDetailsRequestService).createSubscriberCallDetails(callDetailsReportRequest);
    }
}
