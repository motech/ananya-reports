package org.motechproject.ananya.reports.testdata.contract;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;

public class SubscriptionStateChangeRequestBuilder {
    private String subscriptionId;
    private String subscriptionStatus;
    private String reason;
    private DateTime createdAt;
    private String operator;
    private Integer graceCount;
    private Integer weekNumber;


    public SubscriptionStateChangeRequestBuilder(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        this.reason = "Create by script";
        this.graceCount = 0;
        this.createdAt = DateTime.now();
        this.weekNumber = 1;
    }

    public SubscriptionStateChangeRequest build(){
        return new SubscriptionStateChangeRequest(subscriptionId,subscriptionStatus,reason,createdAt,operator,graceCount,weekNumber);
    }

    public SubscriptionStateChangeRequestBuilder withSubscriptionStatus(String status) {
        this.subscriptionStatus = status;
        return this;
    }

    public SubscriptionStateChangeRequestBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public SubscriptionStateChangeRequestBuilder withCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SubscriptionStateChangeRequestBuilder withOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public SubscriptionStateChangeRequestBuilder withGraceCount(Integer graceCount) {
        this.graceCount = graceCount;
        return this;
    }

    public SubscriptionStateChangeRequestBuilder withWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
        return this;
    }

}
