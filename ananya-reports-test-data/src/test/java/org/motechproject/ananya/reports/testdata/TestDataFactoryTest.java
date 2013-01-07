package org.motechproject.ananya.reports.testdata;


import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.reports.kilkari.contract.request.*;
import org.motechproject.ananya.reports.testdata.contract.request.builder.SubscriptionReportRequestBuilder;
import org.motechproject.ananya.reports.testdata.contract.request.builder.SubscriptionStateChangeRequestBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportsTestDataContext.xml")
@Ignore
public class TestDataFactoryTest {

    private ArrayList<String> operators;
    private ArrayList<String> packs;

    @Before
    public void setup(){
        operators = new ArrayList<String>() {{
            add("RELIANCEGSM");
            add("IDEA");
            add("VODAFONE");
            add("TATADOCOMO");
            add("AIRTEL");
            add("BSNL");
        }};

        packs = new ArrayList<String>() {{
            add("BARI_KILKARI");
            add("NANHI_KILKARI");
            add("NAVJAAT_KILKARI");
        }};
    }

    @Ignore
    @Test
    public void shouldAddSampleSubscription() {
        SubscriptionReportRequest subscriptionReportRequest = buildSubscriptionReportRequestForCC("AIRTEL", "NANHI_KILKARI");
        TestDataFactory testDataFactory = new TestDataFactory();
        assertTrue(testDataFactory.addSubscription(subscriptionReportRequest));
    }

    @Ignore
    @Test
    public void shouldActivateSubscription() {
        SubscriptionReportRequest subscriptionReportRequest = buildSubscriptionReportRequestForIVR("AIRTEL", "BARI_KILKARI");
        TestDataFactory testDataFactory = new TestDataFactory();
        testDataFactory.addSubscription(subscriptionReportRequest);
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).
                withOperator("AIRTEL").withSubscriptionStatus("ACTIVE").build();
        testDataFactory.activateSubscription(subscriptionStateChangeRequest);

        CampaignScheduleAlertRequest campaignScheduleAlertRequest = new CampaignScheduleAlertRequest(subscriptionReportRequest.getSubscriptionId(), "WEEK1", DateTime.now().plusDays(1));
        testDataFactory.scheduleCampaignAlert(campaignScheduleAlertRequest);
    }

    private void simulateFlow(String operator, String packname, long channel) {

        DateTime startTime = DateTime.now();

        SubscriptionReportRequest subscriptionReportRequest = channel == 0 ?
                buildSubscriptionReportRequestForIVR(operator, packname) : buildSubscriptionReportRequestForCC(operator,packname);
        TestDataFactory testDataFactory = new TestDataFactory();
        testDataFactory.addSubscription(subscriptionReportRequest);

        SubscriptionStateChangeRequest activate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).
                withSubscriptionStatus("ACTIVE").withOperator(operator).build();
        testDataFactory.changeSubscriptionStatus(activate);

        CampaignScheduleAlertRequest firstAlert = new CampaignScheduleAlertRequest(subscriptionReportRequest.getSubscriptionId(), "WEEK1", startTime.plusDays(1));
        testDataFactory.scheduleCampaignAlert(firstAlert);

        CallDetailRecordRequest callDetailRecord = new CallDetailRecordRequest(startTime, startTime.plusMinutes(5));
        CallDetailsReportRequest firstMessage = new CallDetailsReportRequest(subscriptionReportRequest.getSubscriptionId(), String.valueOf(subscriptionReportRequest.getMsisdn()), "WEEK1", "HANGUP", "0", "SUCCESS", callDetailRecord, "OBD");
        testDataFactory.doOBDCall(firstMessage);

        SubscriptionStateChangeRequest renew = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).
                withSubscriptionStatus("ACTIVE").withCreatedAt(startTime.plusDays(1)).withOperator(operator).withWeekNumber(2).build();
        testDataFactory.changeSubscriptionStatus(renew);

        SubscriptionStateChangeRequest suspend = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).withSubscriptionStatus("SUSPENDED").
                withCreatedAt(startTime.plusDays(4)).withOperator(operator).withWeekNumber(5).build();
        testDataFactory.changeSubscriptionStatus(suspend);

        SubscriptionStateChangeRequest activateAgain = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).withSubscriptionStatus("ACTIVE").
                withCreatedAt(startTime.plusDays(6)).withOperator(operator).withWeekNumber(7).build();
        testDataFactory.changeSubscriptionStatus(activateAgain);

        SubscriptionStateChangeRequest deactivate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).withSubscriptionStatus("DEACTIVATED").
                withCreatedAt(startTime.plusDays(6)).withOperator(operator).withWeekNumber(7).build();
        testDataFactory.changeSubscriptionStatus(deactivate);
    }

    @Ignore
    @Test
    public void shouldGenerateRandomData()
    {
        for(int i=0;i<1000;i++){
            long randomNumber = RandomUtils.nextInt();

            String operator = operators.get((int) (randomNumber % operators.size()));
            long channel = randomNumber % 2;
            String pack = packs.get((int) (randomNumber % packs.size()));

            System.out.println(randomNumber+" "+operator+" "+channel+" "+pack);
            simulateFlow(operator,pack,channel);
        }
    }

    private SubscriptionReportRequest buildSubscriptionReportRequestForCC(String operator, String pack) {
        return new SubscriptionReportRequestBuilder().withChannel("CONTACT_CENTER").withStatus("NEW_EARLY").withEstimatedDateOfDelivery(DateTime.now().plusMonths(5)).withOperator(operator).withPack(pack).build();
    }

    private SubscriptionReportRequest buildSubscriptionReportRequestForIVR(String operator, String pack) {
        return new SubscriptionReportRequestBuilder().withChannel("IVR").withStatus("NEW").withOperator(operator).withPack(pack).build();
    }

}
