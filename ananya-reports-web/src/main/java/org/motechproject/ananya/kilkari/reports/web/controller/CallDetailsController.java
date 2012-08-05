package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.contract.request.CallDetailsRequest;
import org.motechproject.ananya.kilkari.reports.service.CallDetailsRequestService;
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
    public void createSubscriberCallDetails(@RequestBody CallDetailsRequest callDetailsRequest) {
        callDetailsRequestService.createSubscriberCallDetails(callDetailsRequest);
    }
}
