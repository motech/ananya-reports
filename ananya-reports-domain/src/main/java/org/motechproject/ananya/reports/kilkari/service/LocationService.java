package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.contract.request.LocationRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationSyncRequest;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    private AllLocationDimensions allLocationDimensions;
    private SubscriberService subscriberService;

    public LocationService() {
    }

    @Autowired
    public LocationService(AllLocationDimensions allLocationDimensions, SubscriberService subscriberService) {
        this.allLocationDimensions = allLocationDimensions;
        this.subscriberService = subscriberService;
    }

    @Transactional
    public LocationDimension fetchFor(String district, String block, String panchayat) {
        return allLocationDimensions.fetchFor(district, block, panchayat);
    }

    @Transactional
    public void addOrUpdate(LocationSyncRequest locationSyncRequest) {
        LocationStatus locationStatus = LocationStatus.getFor(locationSyncRequest.getLocationStatus());
        LocationRequest actualLocationRequest = locationSyncRequest.getActualLocation();
        LocationDimension actualLocation = allLocationDimensions.fetchFor(actualLocationRequest.getDistrict(), actualLocationRequest.getBlock(), actualLocationRequest.getPanchayat());
        if (locationStatus.equals(LocationStatus.INVALID)) {
            createNewLocation(locationSyncRequest, actualLocation);
        }
        createOrUpdateExistingLocation(actualLocation, actualLocationRequest, locationSyncRequest.getLocationStatus());
    }

    private void createOrUpdateExistingLocation(LocationDimension actualLocation, LocationRequest actualLocationRequest, String locationStatus) {
        actualLocation = actualLocation == null ?
                new LocationDimension(actualLocationRequest.getDistrict(), actualLocationRequest.getBlock(), actualLocationRequest.getPanchayat(), locationStatus)
                : actualLocation;
        actualLocation.setStatus(locationStatus);
        allLocationDimensions.createOrUpdate(actualLocation);
    }

    private void createNewLocation(LocationSyncRequest locationSyncRequest, LocationDimension actualLocation) {
        LocationRequest newLocationRequest = locationSyncRequest.getNewLocation();
        LocationDimension newLocation = new LocationDimension(newLocationRequest.getDistrict(), newLocationRequest.getBlock(), newLocationRequest.getPanchayat(), LocationStatus.VALID.name());
        allLocationDimensions.createOrUpdate(newLocation);
        remapSubscribers(actualLocation, newLocation);
    }

    private void remapSubscribers(LocationDimension oldLocation, LocationDimension newLocation) {
        subscriberService.updateLocation(oldLocation, newLocation);
    }
}
