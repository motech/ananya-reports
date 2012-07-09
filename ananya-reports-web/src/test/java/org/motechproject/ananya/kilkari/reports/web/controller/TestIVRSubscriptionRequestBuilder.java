package org.motechproject.ananya.kilkari.reports.web.controller;

import org.junit.Ignore;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequestBuilder;
@Ignore
public class TestIVRSubscriptionRequestBuilder implements SubscriptionRequestBuilder {


    @Override
    public SubscriptionRequest getSubscriptionRequest() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setSubscriptionId("subscriptionId");
        subscriptionRequest.setChannel("IVR");
        subscriptionRequest.setMsisdn(9090909090L);
        subscriptionRequest.setPack("PCK1");
        return subscriptionRequest;
    }
}
