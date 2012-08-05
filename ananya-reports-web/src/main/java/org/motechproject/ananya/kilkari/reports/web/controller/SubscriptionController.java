package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.contract.response.SubscriptionResponse;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscription;
import org.motechproject.ananya.kilkari.reports.service.SubscriberService;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionService;
import org.motechproject.ananya.kilkari.reports.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.kilkari.reports.web.mapper.SubscriptionMapper;
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
    public void createSubscription(@RequestBody SubscriptionReportRequest subscriptionReportRequest) {
        subscriptionStatusMeasureService.create(subscriptionReportRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/subscriber/{subscriptionId}")
    @ResponseBody
    public void updateSubscriber(@RequestBody SubscriberReportRequest subscriberReportRequest, @PathVariable String subscriptionId) {
        subscriberService.update(subscriberReportRequest, subscriptionId);
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
