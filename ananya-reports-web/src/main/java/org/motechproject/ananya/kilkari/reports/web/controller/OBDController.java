package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.internal.OBDRequest;
import org.motechproject.ananya.kilkari.reports.service.OBDRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OBDController {

    private OBDRequestService obdRequestService;

    @Autowired
    public OBDController(OBDRequestService obdRequestService) {

        this.obdRequestService = obdRequestService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/obd/callDetails")
    @ResponseBody
    public void createSubscriberCallDetails(@RequestBody OBDRequest obdRequest) {

        obdRequestService.createSubscriberCallDetails(obdRequest);
    }
}
