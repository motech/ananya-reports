package org.motechproject.ananya.reports.testdata;

import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignScheduleAlertRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Properties;

@Component
public class TestDataFactory {

    @Autowired
    private Properties dataSetupProperties;

    private RestTemplate restTemplate;

    public TestDataFactory() {
        restTemplate = new RestTemplate();
    }

    public boolean addSubscription(SubscriptionReportRequest subscriptionReportRequest) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl() +
                "/subscription", subscriptionReportRequest, String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    public boolean scheduleCampaignAlert(CampaignScheduleAlertRequest campaignScheduleAlertRequest){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl() + "/subscription/campaignScheduleAlert", campaignScheduleAlertRequest, String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    public void changeSubscriptionStatus(SubscriptionStateChangeRequest subscriptionStateChangeRequest){
        restTemplate.put(baseUrl() +
                "/subscription/" + subscriptionStateChangeRequest.getSubscriptionId(), subscriptionStateChangeRequest, new HashMap<String, Object>());
    }

    public boolean doOBDCall(CallDetailsReportRequest callDetailsReportRequest){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl() + "/callDetails", callDetailsReportRequest, String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    public String baseUrl(){
       return dataSetupProperties.getProperty("baseurl");
    }
}
