package org.motechproject.ananya.reports.web.kilkari.controller;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberCareReportRequest;
import org.motechproject.ananya.reports.kilkari.service.SubscriberCareHelpService;
import org.motechproject.ananya.reports.web.util.TestUtils;
import org.springframework.http.MediaType;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.ananya.reports.web.util.MVCTestUtils.mockMvc;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class SubscriberCareHelpControllerTest {

    private SubscriberCareHelpController subscriberCareHelpController;
    @Mock
    private SubscriberCareHelpService subscriberCareHelpService;

    @Before
    public void setUp() {
        initMocks(this);
        subscriberCareHelpController = new SubscriberCareHelpController(subscriberCareHelpService);
    }

    @Test
    public void shouldCreateSubscriptionCareRequest() throws Exception {

        SubscriberCareReportRequest subscriberCareReportRequest = new SubscriberCareReportRequest("msisdn", "HELP", "channel", DateTime.now());
        ArgumentCaptor<SubscriberCareReportRequest> captor = ArgumentCaptor.forClass(SubscriberCareReportRequest.class);
        String requestJson = TestUtils.toJson(subscriberCareReportRequest);

        mockMvc(subscriberCareHelpController)
                .perform(post("/help")
                        .body(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(subscriberCareHelpService).createHelpRequest(captor.capture());
        SubscriberCareReportRequest actualCareReportRequest = captor.getValue();
        assertEquals(subscriberCareReportRequest.getMsisdn(), actualCareReportRequest.getMsisdn());
        assertEquals(subscriberCareReportRequest.getChannel(), actualCareReportRequest.getChannel());
        assertEquals(subscriberCareReportRequest.getReason(), actualCareReportRequest.getReason());
        assertEquals(subscriberCareReportRequest.getCreatedAt().getMillis(), actualCareReportRequest.getCreatedAt().getMillis());
    }
}
