package org.motechproject.ananya.reports.testdata;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.*;
import org.motechproject.ananya.reports.testdata.contract.request.builder.SubscriptionReportRequestBuilder;
import org.motechproject.ananya.reports.testdata.contract.request.builder.SubscriptionStateChangeRequestBuilder;

public abstract  class BaseFactory {
    protected void simulateDeactivationFlow(SubscriptionData subscriptionData) {
        String operator = subscriptionData.getOperator();
        String packname = subscriptionData.getPackname();
        DateTime startTime = subscriptionData.getStartTime();

        SubscriptionReportRequest subscriptionReportRequest = subscriptionData.getChannel() == 0 ?
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

        CallDetailsReportRequest secondMessage = new CallDetailsReportRequest(subscriptionReportRequest.getSubscriptionId(), String.valueOf(subscriptionReportRequest.getMsisdn()), "WEEK1", "HANGUP", "0", "SUCCESS", callDetailRecord, "OBD");
        testDataFactory.doOBDCall(secondMessage);

        SubscriptionStateChangeRequest deactivate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).withSubscriptionStatus("DEACTIVATED").
                withCreatedAt(startTime.plusDays(6)).withOperator(operator).withWeekNumber(7).build();
        testDataFactory.changeSubscriptionStatus(deactivate);
    }

    protected void simulateActivationWithSuspension(SubscriptionData subscriptionData) {
        String operator = subscriptionData.getOperator();
        String packname = subscriptionData.getPackname();
        DateTime startTime = subscriptionData.getStartTime();

        SubscriptionReportRequest subscriptionReportRequest = subscriptionData.getChannel() == 0 ?
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

        CallDetailsReportRequest secondMessage = new CallDetailsReportRequest(subscriptionReportRequest.getSubscriptionId(), String.valueOf(subscriptionReportRequest.getMsisdn()), "WEEK1", "HANGUP", "0", "SUCCESS", callDetailRecord, "OBD");
        testDataFactory.doOBDCall(secondMessage);
    }

    protected void simulateCompleteFlow(SubscriptionData subscriptionData) {

        String operator = subscriptionData.getOperator();
        String packname = subscriptionData.getPackname();
        DateTime startTime = subscriptionData.getStartTime();

        SubscriptionReportRequest subscriptionReportRequest = subscriptionData.getChannel() == 0 ?
                buildSubscriptionReportRequestForIVR(operator, packname) :
                buildSubscriptionReportRequestForCC(operator, packname);
        TestDataFactory testDataFactory = new TestDataFactory();
        testDataFactory.addSubscription(subscriptionReportRequest);

        for (int i = 0; i < 64; i++) {
            SubscriptionStateChangeRequest activate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).
                    withSubscriptionStatus("ACTIVE").withOperator(operator).build();
            testDataFactory.changeSubscriptionStatus(activate);

            String currentWeek = "WEEK" +(i+1);
            CampaignScheduleAlertRequest alert = new CampaignScheduleAlertRequest(subscriptionReportRequest.getSubscriptionId(), currentWeek, startTime.plusDays(1));
            testDataFactory.scheduleCampaignAlert(alert);

            CallDetailRecordRequest callDetailRecord = new CallDetailRecordRequest(startTime, startTime.plusMinutes(5));
            CallDetailsReportRequest message = new CallDetailsReportRequest(subscriptionReportRequest.getSubscriptionId(), String.valueOf(subscriptionReportRequest.getMsisdn()), currentWeek, "HANGUP", "0", "SUCCESS", callDetailRecord, "OBD");
            testDataFactory.doOBDCall(message);
        }

        SubscriptionStateChangeRequest activate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).
                withSubscriptionStatus("COMPLETE").withOperator(operator).build();
        testDataFactory.changeSubscriptionStatus(activate);
    }

    protected void simulateSuspendSubscription(SubscriptionData subscriptionData){
        String operator = subscriptionData.getOperator();
        String packname = subscriptionData.getPackname();
        DateTime startTime = subscriptionData.getStartTime();

        SubscriptionReportRequest subscriptionReportRequest = subscriptionData.getChannel() == 0 ?
                buildSubscriptionReportRequestForIVR(operator, packname) : buildSubscriptionReportRequestForCC(operator,packname);
        TestDataFactory testDataFactory = new TestDataFactory();
        testDataFactory.addSubscription(subscriptionReportRequest);

        SubscriptionStateChangeRequest activate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).
                withSubscriptionStatus("ACTIVE").withOperator(operator).build();
        testDataFactory.changeSubscriptionStatus(activate);

        SubscriptionStateChangeRequest suspend = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).withSubscriptionStatus("SUSPENDED").
                withCreatedAt(startTime.plusDays(1)).withOperator(operator).withWeekNumber(5).build();
        testDataFactory.changeSubscriptionStatus(suspend);
    }

    protected void simulateChangeSubscriptionSchedule(SubscriptionData subscriptionData){
        String operator = subscriptionData.getOperator();
        String packname = subscriptionData.getPackname();
        DateTime startTime = subscriptionData.getStartTime();

        SubscriptionReportRequest subscriptionReportRequest = subscriptionData.getChannel() == 0 ?
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

        SubscriptionReportRequest changeRequest = new SubscriptionReportRequestBuilder().withChannel("CONTACT_CENTER").withStatus("NEW_EARLY").withEstimatedDateOfDelivery(DateTime.now().plusMonths(5)).withOperator(operator).withPack(packname).withOldSubscriptionId(subscriptionReportRequest.getSubscriptionId()).build();
        testDataFactory.addSubscription(changeRequest);

        SubscriptionStateChangeRequest deactivate = new SubscriptionStateChangeRequestBuilder(subscriptionReportRequest.getSubscriptionId()).withSubscriptionStatus("DEACTIVATED")
                .withOperator(operator).withWeekNumber(7).build();
        testDataFactory.changeSubscriptionStatus(deactivate);

        SubscriptionStateChangeRequest activateNewSubscription = new SubscriptionStateChangeRequestBuilder(changeRequest.getSubscriptionId()).
                withSubscriptionStatus("ACTIVE").withOperator(operator).build();
        testDataFactory.changeSubscriptionStatus(activateNewSubscription);
    }

    private SubscriptionReportRequest buildSubscriptionReportRequestForCC(String operator, String pack) {
        return new SubscriptionReportRequestBuilder().withChannel("CONTACT_CENTER").withStatus("NEW_EARLY").withEstimatedDateOfDelivery(DateTime.now().plusMonths(5)).withOperator(operator).withPack(pack).build();
    }

    private SubscriptionReportRequest buildSubscriptionReportRequestForIVR(String operator, String pack) {
        return new SubscriptionReportRequestBuilder().withChannel("IVR").withStatus("NEW").withOperator(operator).withPack(pack).build();
    }
}
