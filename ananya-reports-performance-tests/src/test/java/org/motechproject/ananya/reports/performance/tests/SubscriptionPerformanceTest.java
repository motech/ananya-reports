package org.motechproject.ananya.reports.performance.tests;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.junit.runner.RunWith;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.reports.performance.tests.utils.BasePerformanceTest;
import org.motechproject.ananya.reports.performance.tests.utils.HttpUtils;
import org.motechproject.performance.tests.LoadRunner;
import org.motechproject.performance.tests.LoadTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RunWith(LoadRunner.class)
public class SubscriptionPerformanceTest extends BasePerformanceTest {

    public SubscriptionPerformanceTest(String name) {
        super(name);
    }

    @LoadTest(concurrentUsers = 10)
    public void shouldCreateAndUpdateASubscription() throws InterruptedException {
        SubscriptionReportRequest subscriptionReportRequest = createSubscriptionRequest();

        ResponseEntity<String> creationResponse = HttpUtils.httpPost(null, subscriptionReportRequest, "subscription");
        assertEquals(HttpStatus.OK, creationResponse.getStatusCode());

        SubscriptionStateChangeRequest stateChangeRequest = createStateChangeRequest(subscriptionReportRequest);

        try {
            HttpUtils.httpPut(stateChangeRequest, "subscription", stateChangeRequest.getSubscriptionId());
        } catch (Exception e) {
            fail("Updation of subscription failed. Exception : " + e.getStackTrace());
        }
    }

    private SubscriptionStateChangeRequest createStateChangeRequest(SubscriptionReportRequest createRequest) {
        return new SubscriptionStateChangeRequest(createRequest.getSubscriptionId(), "PENDING_ACTIVATION", "Any Reason", DateTime.now(), createRequest.getOperator(), 0);
    }

    private SubscriptionReportRequest createSubscriptionRequest() {
        SubscriptionReportRequest request = new SubscriptionReportRequest(UUID.randomUUID().toString(), "IVR", getRandomMsisdn(), "NANHI_KILKARI", null, null, DateTime.now(), "NEW", DateTime.now().plusDays(2),
                null, null, "AIRTEL", DateTime.now(), null, "Reason");
        return request;
    }

    private Long getRandomMsisdn() {
        String msisdn = "9" + RandomStringUtils.randomNumeric(9);
        return Long.valueOf(msisdn);
    }
}