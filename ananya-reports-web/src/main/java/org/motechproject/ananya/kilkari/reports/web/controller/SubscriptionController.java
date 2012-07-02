package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubscriptionController {

    private SubscriptionRequestService subscriptionRequestService;

    @Autowired
    public SubscriptionController(SubscriptionRequestService subscriptionRequestService) {
        this.subscriptionRequestService = subscriptionRequestService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscription")
    @ResponseBody
    public void createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionRequestService.createSubscription(subscriptionRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatesubscription/{subscriptionId}")
    @ResponseBody
    public void updateSubscription(@RequestBody SubscriptionStateChangeRequest subscriptionStateChangeRequest, @PathVariable String subscriptionId) {
        subscriptionRequestService.updateSubscription(subscriptionStateChangeRequest);
    }
}
