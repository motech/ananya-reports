package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;

public class LocationSyncRequest {
    private LocationRequest existingLocation;
    private LocationRequest newLocation;
    private String locationStatus;
    private DateTime lastModifiedTime;

    public LocationSyncRequest() {
    }

    public LocationSyncRequest(LocationRequest existingLocation, LocationRequest newLocation, String locationStatus, DateTime lastModifiedTime) {
        this.existingLocation = existingLocation;
        this.newLocation = newLocation;
        this.locationStatus = locationStatus;
        this.lastModifiedTime = lastModifiedTime;
    }

    public LocationRequest getExistingLocation() {
        return existingLocation;
    }

    public LocationRequest getNewLocation() {
        return newLocation;
    }

    public String getLocationStatus() {
        return locationStatus;
    }

    public DateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        return "LocationSyncRequest{" +
                "existingLocation=" + existingLocation +
                ", newLocation=" + newLocation +
                ", locationStatus='" + locationStatus +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
