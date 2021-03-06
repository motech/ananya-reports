package org.motechproject.ananya.reports.web.kilkari.controller;

import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.motechproject.ananya.reports.kilkari.service.CallDetailsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CallDetailsController {

    private CallDetailsRequestService callDetailsRequestService;

    @Autowired
    public CallDetailsController(CallDetailsRequestService callDetailsRequestService) {
        this.callDetailsRequestService = callDetailsRequestService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/callDetails")
    @ResponseBody
    public void createSubscriberCallDetails(@RequestBody CallDetailsReportRequest callDetailsReportRequest) {
        callDetailsRequestService.createSubscriberCallDetails(callDetailsReportRequest);
    }
}
