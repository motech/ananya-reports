package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.kilkari.reports.web.mapper.SubscriptionStatusMeasureMapper;
import org.motechproject.ananya.kilkari.reports.web.response.SubscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SubscriptionController {

    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionStatusMeasureMapper subscriptionStatusMeasureMapper;

    @Autowired
    public SubscriptionController(SubscriptionStatusMeasureService subscriptionStatusMeasureService) {
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscription")
    @ResponseBody
    public void createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionStatusMeasureService.create(subscriptionRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatesubscription/{subscriptionId}")
    @ResponseBody
    public void updateSubscription(@RequestBody SubscriptionStateChangeRequest subscriptionStateChangeRequest, @PathVariable String subscriptionId) {
        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subscription")
    @ResponseBody
    public List<SubscriptionResponse> getSubscriptions(@RequestParam String msisdn) {
        return null;
    }
}
