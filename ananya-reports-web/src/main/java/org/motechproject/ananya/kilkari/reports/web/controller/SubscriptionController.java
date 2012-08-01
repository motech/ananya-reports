package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.internal.SubscriberRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.service.SubscriberService;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionService;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.kilkari.reports.web.mapper.SubscriptionMapper;
import org.motechproject.ananya.kilkari.reports.web.response.SubscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriptionController {

    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    private SubscriptionService subscriptionService;
    private SubscriberService subscriberService;

    @Autowired
    public SubscriptionController(SubscriptionStatusMeasureService subscriptionStatusMeasureService, SubscriptionService subscriptionService, SubscriberService subscriberService) {
        this.subscriptionStatusMeasureService = subscriptionStatusMeasureService;
        this.subscriptionService = subscriptionService;
        this.subscriberService = subscriberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscription")
    @ResponseBody
    public void createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionStatusMeasureService.create(subscriptionRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/subscriber/{subscriptionId}")
    @ResponseBody
    public void updateSubscriber(@RequestBody SubscriberRequest subscriberRequest, @PathVariable String subscriptionId) {
        subscriberService.update(subscriberRequest, subscriptionId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/subscription/{subscriptionId}")
    @ResponseBody
    public void updateSubscription(@RequestBody SubscriptionStateChangeRequest subscriptionStateChangeRequest, @PathVariable String subscriptionId) {
        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subscriber")
    @ResponseBody
    public List<SubscriptionResponse> getSubscriptions(@RequestParam String msisdn) {
        List<Subscription> subscriptionList = subscriptionService.findByMsisdn(msisdn);
        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
        for (Subscription subscription : subscriptionList) {
            subscriptionResponseList.add(SubscriptionMapper.mapFrom(subscription));
        }
        return subscriptionResponseList;
    }
}
