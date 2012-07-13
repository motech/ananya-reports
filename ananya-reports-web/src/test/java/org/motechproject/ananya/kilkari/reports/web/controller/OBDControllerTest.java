package org.motechproject.ananya.kilkari.reports.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.OBDRequest;
import org.motechproject.ananya.kilkari.reports.service.OBDRequestService;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class OBDControllerTest {

    private OBDController obdController;
    @Mock
    private OBDRequestService obdRequestService;

    @Before
    public void setUp(){
        initMocks(this);
        obdController = new OBDController(obdRequestService);
    }

    @Test
    public void shouldCreateSubscriberCallDetails() throws Exception {
        OBDRequest obdRequest = new OBDRequest();

        obdController.createSubscriberCallDetails(obdRequest);

        verify(obdRequestService).createSubscriberCallDetails(obdRequest);
    }
}
