package org.motechproject.ananya.reports.web.kilkari.controller;

import org.motechproject.ananya.reports.kilkari.contract.request.LocationSyncRequest;
import org.motechproject.ananya.reports.kilkari.contract.response.LocationResponse;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.service.LocationService;
import org.motechproject.ananya.reports.web.kilkari.exceptions.NotFoundException;
import org.motechproject.ananya.reports.web.kilkari.exceptions.ValidationException;
import org.motechproject.ananya.reports.web.kilkari.validator.Errors;
import org.motechproject.ananya.reports.web.kilkari.validator.LocationSyncRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocationController {
    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    @ResponseBody
    public LocationResponse getLocation(@RequestParam(required = false) String district, @RequestParam(required = false) String block, @RequestParam(required = false) String panchayat) {
        LocationDimension locationDimension = locationService.digDeepAndFetchFor(district, block, panchayat);
        if (locationDimension == null) {
            throw new NotFoundException("location not found");
        }
        return new LocationResponse(locationDimension.getDistrict(), locationDimension.getBlock(), locationDimension.getPanchayat());
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public
    @ResponseBody
    void addOrUpdateLocation(@RequestBody LocationSyncRequest locationSyncRequest) {
        Errors errors = LocationSyncRequestValidator.validate(locationSyncRequest);
        raiseExceptionOnError(errors);
        locationService.addOrUpdate(locationSyncRequest);
    }

    private void raiseExceptionOnError(Errors errors) {
        if (errors.hasErrors())
            throw new ValidationException(errors.allMessages());
    }
}
