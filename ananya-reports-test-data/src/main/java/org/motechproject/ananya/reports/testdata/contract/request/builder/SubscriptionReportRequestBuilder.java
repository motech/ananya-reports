package org.motechproject.ananya.reports.testdata.contract.request.builder;


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


    public SubscriptionReportRequestBuilder() {
        String msisdn = "1"+ RandomStringUtils.randomNumeric(9);

        this.subscriptionId = UUID.randomUUID().toString();
        this.msisdn = Long.parseLong(msisdn);
        this.name = "TestName";
        this.ageOfBeneficiary = 25;
        this.createdAt = DateTime.now();
        this.subscriptionStatus = "NEW";
        this.location = new SubscriberLocation("testDistrict", "testBlock", "testPanchayat");
        this.startDate = DateTime.now() ;
        this.oldSubscriptionId = null;
        this.reason = "Script data";
        this.startWeekNumber = 1;
    }

    public SubscriptionReportRequest build() {
        return new SubscriptionReportRequest(subscriptionId, channel, msisdn, pack, name, ageOfBeneficiary, createdAt, subscriptionStatus, estimatedDateOfDelivery, dateOfBirth,
                location, operator, startDate, oldSubscriptionId, reason, startWeekNumber);
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
        this.name = name;
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

    public SubscriptionReportRequestBuilder withLocation(String district, String block, String panchayat){
        this.location = new SubscriberLocation(district, block, panchayat);
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
}
