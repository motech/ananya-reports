package org.motechproject.ananya.reports.testdata.contract;


import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;

import java.util.UUID;

public class SubscriptionReportRequestBuilder {

    private String subscriptionId;
    private String channel;
    private Long msisdn;
    private String pack;
    private String name;
    private Integer ageOfBeneficiary;
    private DateTime createdAt;
    private String subscriptionStatus;
    private DateTime estimatedDateOfDelivery;
    private DateTime dateOfBirth;
    private SubscriberLocation location;
    private String operator;
    private DateTime startDate;
    private String oldSubscriptionId;
    private String reason;
    private Integer startWeekNumber;
    private Long referredByFLWMsisdn;
	private boolean referredByFLWMsisdnFlag; 

    public SubscriptionReportRequestBuilder() {
        String msisdn = "1"+ RandomStringUtils.randomNumeric(9);

        this.subscriptionId = UUID.randomUUID().toString();
        this.msisdn = Long.parseLong(msisdn);
        this.referredByFLWMsisdn = Long.parseLong(RandomStringUtils.randomNumeric(10));
        this.name = "TestName";
        this.ageOfBeneficiary = 25;
        this.createdAt = DateTime.now();
        this.subscriptionStatus = "NEW";
        this.referredByFLWMsisdnFlag = true;

        String testState = "testState"+RandomStringUtils.randomNumeric(100);
        String testDistrict = "testDistrict"+RandomStringUtils.randomNumeric(100);
        String testBlock = "testBlock"+RandomStringUtils.randomNumeric(100);
        String testPanchayat = "testPanchayat"+RandomStringUtils.randomNumeric(100);

        this.location = new SubscriberLocation(testState, testDistrict, testBlock, testPanchayat);
        this.startDate = DateTime.now() ;
        this.oldSubscriptionId = null;
        this.reason = "Script data";
        this.startWeekNumber = 1;
    }

    public SubscriptionReportRequest build() {
        return new SubscriptionReportRequest(subscriptionId, channel, msisdn, pack, name, ageOfBeneficiary, createdAt, subscriptionStatus, estimatedDateOfDelivery, dateOfBirth,
                location, operator, startDate, oldSubscriptionId, reason, startWeekNumber, referredByFLWMsisdn,referredByFLWMsisdnFlag, false);
    }

    public SubscriptionReportRequestBuilder withChannel(String channel){
        this.channel = channel;
        return this;
    }

    public SubscriptionReportRequestBuilder withMSISDN(Long msisdn){
        this.msisdn = msisdn;
        return this;
    }

    public SubscriptionReportRequestBuilder withPack(String pack){
        this.pack = pack;
        return this;
    }

    public SubscriptionReportRequestBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SubscriptionReportRequestBuilder withAgeOfBeneficiary(Integer ageOfBeneficiary){
        this.ageOfBeneficiary = ageOfBeneficiary;
        return this;
    }

    public SubscriptionReportRequestBuilder withCreatedAt(DateTime createdAt){
        this.createdAt = createdAt;
        return this;
    }

    public SubscriptionReportRequestBuilder withStatus(String status) {
        this.subscriptionStatus = status;
        return this;
    }

    public SubscriptionReportRequestBuilder withEstimatedDateOfDelivery(DateTime estimatedDateOfDelivery){
        this.estimatedDateOfDelivery = estimatedDateOfDelivery;
        return this;
    }

    public SubscriptionReportRequestBuilder withDateOfBirth(DateTime dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public SubscriptionReportRequestBuilder withOperator(String operator){
        this.operator = operator;
        return this;
    }

    public SubscriptionReportRequestBuilder withStartDate(DateTime startDate){
        this.startDate = startDate;
        return this;
    }

    public SubscriptionReportRequestBuilder withReason(String reason){
        this.reason = reason;
        return this;
    }

    public SubscriptionReportRequestBuilder withStartWeekNumber(Integer startWeekNumber){
        this.startWeekNumber = startWeekNumber;
        return this;
    }

    public SubscriptionReportRequestBuilder withOldSubscriptionId(String oldSubscriptionId){
        this.oldSubscriptionId = oldSubscriptionId;
        return this;
    }

    public SubscriptionReportRequestBuilder withReferredByFLWMsisdn(Long referredByFLWMsisdn){
        this.referredByFLWMsisdn = referredByFLWMsisdn;
        return this;
    }
    
    public SubscriptionReportRequestBuilder withReferredByFLWMsisdnFlag(boolean referredByFLWMsisdnFlag){
        this.referredByFLWMsisdnFlag = referredByFLWMsisdnFlag;
        return this;
    }
}
