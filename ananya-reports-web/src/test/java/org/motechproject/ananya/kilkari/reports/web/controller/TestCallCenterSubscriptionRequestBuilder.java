package org.motechproject.ananya.kilkari.reports.web.controller;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.motechproject.ananya.kilkari.internal.SubscriberLocation;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequestBuilder;
@Ignore
public class TestCallCenterSubscriptionRequestBuilder implements SubscriptionRequestBuilder {

    @Override
    public SubscriptionRequest getSubscriptionRequest() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setSubscriptionId("subscriptionId");
        subscriptionRequest.setChannel("callcenter");
        subscriptionRequest.setMsisdn("9090909090");
        subscriptionRequest.setPack("PCK1");
        subscriptionRequest.setAgeOfBeneficiary(24);
        subscriptionRequest.setEstimatedDateOfDelivery(DateTime.now());
        subscriptionRequest.setDateOfBirth(DateTime.now());
        subscriptionRequest.setLocation(new SubscriberLocation("district", "block", "panchayat"));
        return subscriptionRequest;
    }

}
