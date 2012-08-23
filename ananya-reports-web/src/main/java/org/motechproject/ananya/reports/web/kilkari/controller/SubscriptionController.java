package org.motechproject.ananya.reports.web.kilkari.controller;

import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriberResponse;
import org.motechproject.ananya.reports.kilkari.contract.response.SubscriptionResponse;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.service.SubscriberService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionStatusMeasureService;
import org.motechproject.ananya.reports.web.kilkari.controller.mapper.SubscriberMapper;
import org.motechproject.ananya.reports.web.kilkari.controller.mapper.SubscriptionResponseMapper;
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
        subscriptionStatusMeasureService.createSubscription(subscriptionReportRequest);
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
            subscriptionResponseList.add(SubscriptionResponseMapper.mapFrom(subscription));
        }
        return subscriptionResponseList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subscription/subscriber/{subscriptionId}")
    @ResponseBody
    public SubscriberResponse getSubscriber(@PathVariable String subscriptionId) {
        Subscription subscription = subscriptionService.fetchFor(subscriptionId);
        return SubscriberMapper.mapFrom(subscription);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscription/changemsisdn")
    @ResponseBody
    public void updateSubscriptionForChangedMsisdn(@RequestParam String subscriptionId, @RequestParam String msisdn) {
        Long msisdnAsLong = Long.parseLong(msisdn);
        subscriptionService.changeMsisdnForSubscription(subscriptionId, msisdnAsLong);
    }
}
