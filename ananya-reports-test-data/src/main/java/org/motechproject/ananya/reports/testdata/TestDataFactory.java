package org.motechproject.ananya.reports.testdata;

import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignScheduleAlertRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class TestDataFactory {
    public static final String REPORTS_BASE_URL = "http://localhost:9999/ananya-reports";
    private RestTemplate restTemplate;


    public TestDataFactory() {
        restTemplate = new RestTemplate();
    }

    public boolean addSubscription(SubscriptionReportRequest subscriptionReportRequest) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(REPORTS_BASE_URL +
                "/subscription", subscriptionReportRequest, String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    public void activateSubscription(SubscriptionStateChangeRequest subscriptionStateChangeRequest){
        restTemplate.put(REPORTS_BASE_URL +
                "/subscription/" + subscriptionStateChangeRequest.getSubscriptionId(), subscriptionStateChangeRequest, new HashMap<String, Object>());
    }

    public boolean scheduleCampaignAlert(CampaignScheduleAlertRequest campaignScheduleAlertRequest){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(REPORTS_BASE_URL + "/subscription/campaignScheduleAlert", campaignScheduleAlertRequest, String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    public void changeSubscriptionStatus(SubscriptionStateChangeRequest subscriptionStateChangeRequest){
        restTemplate.put(REPORTS_BASE_URL +
                "/subscription/" + subscriptionStateChangeRequest.getSubscriptionId(), subscriptionStateChangeRequest, new HashMap<String, Object>());
    }

    public boolean doOBDCall(CallDetailsReportRequest callDetailsReportRequest){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(REPORTS_BASE_URL + "/callDetails", callDetailsReportRequest, String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }
}
