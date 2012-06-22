package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    private SubscriptionRequestService subscriptionRequestService;

    @Autowired
    public SubscriptionController(SubscriptionRequestService subscriptionRequestService) {
        this.subscriptionRequestService = subscriptionRequestService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionRequestService.createSubscription(subscriptionRequest);
    }
}
