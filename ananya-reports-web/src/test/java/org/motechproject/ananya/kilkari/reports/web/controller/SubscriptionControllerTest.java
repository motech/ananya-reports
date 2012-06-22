package org.motechproject.ananya.kilkari.reports.web.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionRequestService;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class SubscriptionControllerTest {

    private SubscriptionController subscriptionController;

    @Mock
    private SubscriptionRequestService subscriptionRequestService;

    @Before
    public void setUp() {
        initMocks(this);
        subscriptionController = new SubscriptionController(subscriptionRequestService);
    }

    @Test
    @Ignore
    public void shouldCreateSubscription() throws Exception {

        String subscriptionRequestJson = "{\n" +
                "   \"subscriptionId\":\"sid\",\n" +
                "   \"channel\":\"IVR\",\n" +
                "   \"msisdn\":\"9740123123\",\n" +
                "   \"pack\":\"PCK1\",\n" +
                "   \"name\":\"Sukamma\",\n" +
                "   \"ageOfBeneficiary\":24,\n" +
                "   \"edd\":\"2010-05-05\", \n" +
                "   \"dob\":\"2010-05-05\",\n" +
                "   \"location\":{\n" +
                "      \"district\":\"Patna\",\n" +
                "      \"block\":\"Dulhin Bazar\",\n" +
                "      \"panchayat\":\"Rajipur\"\n" +
                "   }\n" +
                "}";

        MockMvcBuilders.standaloneSetup(subscriptionController).build()
                .perform(post("/subscription/")
                        .body(subscriptionRequestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(subscriptionRequestService).createSubscription(any(SubscriptionRequest.class));
    }
}
