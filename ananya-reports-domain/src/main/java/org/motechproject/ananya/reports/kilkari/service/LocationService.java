package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.contract.request.LocationRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationSyncRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscribers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LocationService {

    private AllLocationDimensions allLocationDimensions;
    private AllSubscribers allSubscribers;

    public LocationService() {
    }

    @Autowired
    public LocationService(AllLocationDimensions allLocationDimensions, AllSubscribers allSubscribers) {
        this.allLocationDimensions = allLocationDimensions;
        this.allSubscribers = allSubscribers;
    }

    @Transactional
    public LocationDimension digDeepAndFetchFor(String district, String block, String panchayat) {
        LocationDimension locationDimension = allLocationDimensions.fetchFor(district, block, panchayat);
        return (locationDimension != null && locationDimension.isInvalidLocation()) ? locationDimension.getAlternateLocation() : locationDimension;
    }

    @Transactional
    public void addOrUpdate(LocationSyncRequest locationSyncRequest) {
        if (isNotLatestRequest(locationSyncRequest)) {
            return;
        }

        LocationStatus locationStatus = LocationStatus.getFor(locationSyncRequest.getLocationStatus());
        LocationRequest actualLocationRequest = locationSyncRequest.getActualLocation();
        LocationDimension actualLocation = allLocationDimensions.fetchFor(actualLocationRequest.getDistrict(), actualLocationRequest.getBlock(), actualLocationRequest.getPanchayat());
        LocationDimension newLocation = null;
        if (locationStatus.equals(LocationStatus.INVALID)) {
            newLocation = createNewLocation(locationSyncRequest, actualLocation);
        }
        createOrUpdateExistingLocation(actualLocation, newLocation, locationSyncRequest);
    }

    //Don't put @Transactional on me. The callers are already @Transactional
    public LocationDimension createAndFetch(SubscriberLocation location) {
        if (location == null) return null;

        LocationDimension locationDimension = digDeepAndFetchFor(location.getDistrict(), location.getBlock(), location.getPanchayat());
        if (locationDimension != null)
            return locationDimension;

        locationDimension = new LocationDimension(location.getDistrict(), location.getBlock(), location.getPanchayat(), LocationStatus.NOT_VERIFIED.name());
        allLocationDimensions.createOrUpdate(locationDimension);
        return locationDimension;
    }

    private boolean isNotLatestRequest(LocationSyncRequest locationSyncRequest) {
        LocationRequest actualLocation = locationSyncRequest.getActualLocation();
        LocationDimension locationDimension = allLocationDimensions.fetchFor(actualLocation.getDistrict(), actualLocation.getBlock(), actualLocation.getPanchayat());
        return locationDimension != null && locationDimension.getLastModified() != null && locationDimension.getLastModified().after(locationSyncRequest.getLastModifiedTime().toDate());
    }

    private void createOrUpdateExistingLocation(LocationDimension actualLocation, LocationDimension newLocation, LocationSyncRequest locationSyncRequest) {
        LocationRequest actualLocationRequest = locationSyncRequest.getActualLocation();
        actualLocation = actualLocation == null ?
                new LocationDimension(actualLocationRequest.getDistrict(), actualLocationRequest.getBlock(), actualLocationRequest.getPanchayat(), locationSyncRequest.getLocationStatus())
                : actualLocation;
        actualLocation.setStatus(locationSyncRequest.getLocationStatus());
        actualLocation.setAlternateLocation(newLocation);
        actualLocation.setLastModified(new Timestamp(locationSyncRequest.getLastModifiedTime().getMillis()));
        allLocationDimensions.createOrUpdate(actualLocation);
    }

    private LocationDimension createNewLocation(LocationSyncRequest locationSyncRequest, LocationDimension actualLocation) {
        LocationRequest newLocationRequest = locationSyncRequest.getNewLocation();
        LocationDimension newLocation = allLocationDimensions.fetchFor(newLocationRequest.getDistrict(), newLocationRequest.getBlock(), newLocationRequest.getPanchayat());
        if (newLocation == null) {
            newLocation = new LocationDimension(newLocationRequest.getDistrict(), newLocationRequest.getBlock(), newLocationRequest.getPanchayat(), LocationStatus.VALID.name());
            allLocationDimensions.createOrUpdate(newLocation);
        }
        remapSubscribers(actualLocation, newLocation);
        return newLocation;
    }

    private void remapSubscribers(LocationDimension oldLocation, LocationDimension newLocation) {
        if (oldLocationIsNotPresent(oldLocation)) return;
        List<Subscriber> subscriberList = allSubscribers.findByLocation(oldLocation);
        for (Subscriber subscriber : subscriberList) {
            subscriber.setLocationDimension(newLocation);
        }
        allSubscribers.saveOrUpdateAll(subscriberList);
    }

    private boolean oldLocationIsNotPresent(LocationDimension oldLocation) {
        return oldLocation == null;
    }
}
