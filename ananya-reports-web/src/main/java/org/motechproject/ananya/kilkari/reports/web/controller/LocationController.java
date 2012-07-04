package org.motechproject.ananya.kilkari.reports.web.controller;

import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.service.LocationService;
import org.motechproject.ananya.kilkari.reports.web.contracts.response.LocationResponse;
import org.motechproject.ananya.kilkari.reports.web.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationController {
    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    @ResponseBody
    public LocationResponse getSubscriptions(@RequestParam String district, @RequestParam String block, @RequestParam String panchayat) {
        LocationDimension locationDimension = locationService.fetchFor(district, block, panchayat);
        if(locationDimension == null) {
            throw new NotFoundException("location not found");
        }
        return new LocationResponse(locationDimension);
    }
}