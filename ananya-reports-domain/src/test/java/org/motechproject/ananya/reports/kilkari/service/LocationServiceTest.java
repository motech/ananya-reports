package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationSyncRequest;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class LocationServiceTest {

    @Mock
    private AllLocationDimensions allLocationDimensions;
    @Mock
    private SubscriberService subscriberService;
    private LocationService locationService;

    @Before
    public void setUp() {
        initMocks(this);
        locationService = new LocationService(allLocationDimensions, subscriberService);
    }

    @Test
    public void shouldFetchLocationForDistrictBlockAndPanchayat() {

        LocationDimension expectedLocationDimension = new LocationDimension("mydistrict", "myblock", "mypanchayat", "VALID", null);
        when(allLocationDimensions.fetchFor("mydistrict", "myblock", "mypanchayat")).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = locationService.fetchFor("mydistrict", "myblock", "mypanchayat");

        assertEquals(expectedLocationDimension, locationDimension);
    }

    @Test
    public void shouldAddOrUpdateLocation() {
        LocationRequest locationRequest = new LocationRequest("d", "b", "p");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());

        locationService.addOrUpdate(new LocationSyncRequest(locationRequest, locationRequest, LocationStatus.VALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions).createOrUpdate(captor.capture());
        LocationDimension actualLocationDimension = captor.getValue();
        assertEquals(new LocationDimension("d", "b", "p", LocationStatus.VALID.name(), lastModifiedTime), actualLocationDimension);
        verify(subscriberService, never()).updateLocation(any(LocationDimension.class), any(LocationDimension.class));
    }

    @Test
    public void shouldUpdateLocationStatus() {
        LocationRequest oldLocationRequest = new LocationRequest("d", "b", "p");
        LocationRequest newLocationRequest = new LocationRequest("d1", "b1", "p1");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());
        LocationDimension expectedOldLocationDimension = new LocationDimension("d", "b", "p", LocationStatus.INVALID.name(), lastModifiedTime);
        LocationDimension expectedNewLocationDimension = new LocationDimension("d1", "b1", "p1", LocationStatus.VALID.name(), lastModifiedTime);
        when(locationService.fetchFor("d", "b", "p")).thenReturn(expectedOldLocationDimension);

        locationService.addOrUpdate(new LocationSyncRequest(oldLocationRequest, newLocationRequest, LocationStatus.INVALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions, times(2)).createOrUpdate(captor.capture());
        List<LocationDimension> actualLocationDimensions = captor.getAllValues();
        assertEquals(expectedNewLocationDimension, actualLocationDimensions.get(0));
        assertEquals(expectedOldLocationDimension, actualLocationDimensions.get(1));
        verify(subscriberService).updateLocation(expectedOldLocationDimension, expectedNewLocationDimension);
    }

    @Test
    public void shouldUpdateLocationOnlyIfLatestRequest() {
        LocationRequest locationRequest = new LocationRequest("d", "b", "p");
        when(allLocationDimensions.fetchFor("d", "b", "p")).thenReturn(new LocationDimension("d", "b", "p", null, new Timestamp(DateTime.now().getMillis())));

        locationService.addOrUpdate(new LocationSyncRequest(locationRequest, locationRequest, LocationStatus.VALID.name(), DateTime.now().minusDays(1)));

        verify(subscriberService, never()).updateLocation(any(LocationDimension.class), any(LocationDimension.class));
        verify(allLocationDimensions, never()).createOrUpdate(any(LocationDimension.class));
    }

    @Test
    public void shouldNotCreateANewLocationIfLocationIsAlreadyPresent() {
        LocationRequest oldLocationRequest = new LocationRequest("d", "b", "p");
        LocationRequest newLocationRequest = new LocationRequest("d1", "b1", "p1");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());
        LocationDimension expectedOldLocationDimension = new LocationDimension("d", "b", "p", LocationStatus.INVALID.name(), lastModifiedTime);
        LocationDimension expectedNewLocationDimension = new LocationDimension("d1", "b1", "p1", LocationStatus.VALID.name(), lastModifiedTime);
        when(allLocationDimensions.fetchFor("d", "b", "p")).thenReturn(expectedOldLocationDimension);
        when(allLocationDimensions.fetchFor("d1", "b1", "p1")).thenReturn(expectedNewLocationDimension);

        locationService.addOrUpdate(new LocationSyncRequest(oldLocationRequest, newLocationRequest, LocationStatus.INVALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions).createOrUpdate(captor.capture());
        List<LocationDimension> actualLocationDimensions = captor.getAllValues();
        assertEquals(expectedOldLocationDimension, actualLocationDimensions.get(0));
        verify(subscriberService).updateLocation(expectedOldLocationDimension, expectedNewLocationDimension);
    }

    @Test
    public void shouldNotRemapSubscribersIfOldLocationIsNotPresent() {
        LocationRequest oldLocationRequest = new LocationRequest("d", "b", "p");
        LocationRequest newLocationRequest = new LocationRequest("d1", "b1", "p1");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());
        LocationDimension expectedOldLocationDimension = new LocationDimension("d", "b", "p", LocationStatus.INVALID.name(), lastModifiedTime);
        LocationDimension expectedNewLocationDimension = new LocationDimension("d1", "b1", "p1", LocationStatus.VALID.name(), lastModifiedTime);
        when(allLocationDimensions.fetchFor("d", "b", "p")).thenReturn(null);
        when(allLocationDimensions.fetchFor("d1", "b1", "p1")).thenReturn(expectedNewLocationDimension);

        locationService.addOrUpdate(new LocationSyncRequest(oldLocationRequest, newLocationRequest, LocationStatus.INVALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions).createOrUpdate(captor.capture());
        List<LocationDimension> actualLocationDimensions = captor.getAllValues();
        assertEquals(expectedOldLocationDimension, actualLocationDimensions.get(0));
        verify(subscriberService, never()).updateLocation(expectedOldLocationDimension, expectedNewLocationDimension);
    }
}
