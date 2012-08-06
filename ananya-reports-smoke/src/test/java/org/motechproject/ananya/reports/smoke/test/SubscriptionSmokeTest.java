package org.motechproject.ananya.reports.smoke.test;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.reports.smoke.contract.SubscriptionRequest;
import org.motechproject.ananya.reports.smoke.repository.ReportingService;
import org.motechproject.ananya.reports.smoke.repository.domain.SubscriptionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportsSmokeContext.xml")
public class SubscriptionSmokeTest {

    public static final String REPORTS_BASE_URL = "http://localhost:8080/ananya-reports";
    private RestTemplate restTemplate;

    @Autowired
    private ReportingService reportService;

    @Before
    public void setUp() throws SQLException {
        restTemplate = new RestTemplate();
        reportService.deleteAll();
    }

    @Test
    public void shouldPostHttpRequestAndVerifyEntriesInReportDbAndCouchDb() throws InterruptedException, SQLException {
        String msisdn = "9740123123";

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setMsisdn(Long.parseLong(msisdn));
        subscriptionRequest.setChannel("IVR");
        subscriptionRequest.setPack("SEVEN_MONTHS");
        subscriptionRequest.setSubscriptionStatus("NEW");
        subscriptionRequest.setCreatedAt(DateTime.now());
        subscriptionRequest.setStartDate(DateTime.now());

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(REPORTS_BASE_URL +
                "/subscription", subscriptionRequest, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<SubscriptionStatus> subscriptionStatusMeasures = reportService.getSubscriptionStatusMeasureForMsisdn(msisdn);
        assertEquals(1, subscriptionStatusMeasures.size());
        assertReportData(subscriptionStatusMeasures, subscriptionRequest);
    }

    private void assertReportData(List<SubscriptionStatus> subscriptionStatusMeasures, SubscriptionRequest subscriptionRequest) {
        SubscriptionStatus subscriptionStatus = subscriptionStatusMeasures.get(0);

        assertEquals(subscriptionRequest.getMsisdn().toString(), subscriptionStatus.getMsisdn());
        assertEquals(subscriptionRequest.getPack(), subscriptionStatus.getPack().toUpperCase());
        assertEquals(subscriptionRequest.getChannel(), subscriptionStatus.getChannel().toUpperCase());
        assertEquals(subscriptionRequest.getSubscriptionStatus(), subscriptionStatus.getStatus().toUpperCase());
    }
}