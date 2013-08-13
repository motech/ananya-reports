package org.motechproject.ananya.reports.web.kilkari.controller;

import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberCareReportRequest;
import org.motechproject.ananya.reports.kilkari.service.SubscriberCareHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubscriberCareHelpController {

    SubscriberCareHelpService subscriberCareHelpService;

    @Autowired
    public SubscriberCareHelpController(SubscriberCareHelpService subscriberCareHelpService) {
        this.subscriberCareHelpService = subscriberCareHelpService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/help")
    @ResponseBody
    public void createSubscriberHelpRequest(@RequestBody SubscriberCareReportRequest subscriberCareReportRequest) {
        subscriberCareHelpService.createHelpRequest(subscriberCareReportRequest);
    }
}
