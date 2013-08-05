package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationSyncRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscribers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class LocationServiceTest {

    @Mock
    private AllLocationDimensions allLocationDimensions;
    @Mock
    private AllSubscribers allSubscribers;
    @Captor
    private ArgumentCaptor<List<Subscriber>> subscribersCaptor;

    private LocationService locationService;

    @Before
    public void setUp() {
        initMocks(this);
        locationService = new LocationService(allLocationDimensions, allSubscribers);
    }

    @Test
    public void shouldFetchLocationForDistrictBlockAndPanchayat() {
        LocationDimension expectedLocationDimension = new LocationDimension("mystate", "mydistrict", "myblock", "mypanchayat", "VALID");
        when(allLocationDimensions.fetchFor("mydistrict", "myblock", "mypanchayat")).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = locationService.digDeepAndFetchFor("mydistrict", "myblock", "mypanchayat");

        assertEquals(expectedLocationDimension, locationDimension);
    }

    @Test
    public void shouldReturnAlternateLocationIfTheExistingLocationIsInvalid() {
        LocationDimension alternateLocation = new LocationDimension("S2", "D2", "B2", "P2", LocationStatus.VALID.name());
        LocationDimension alternateInvalidLocation = new LocationDimension("S1", "D1", "B1", "P1", LocationStatus.INVALID.name());
        alternateInvalidLocation.setAlternateLocation(alternateLocation);
        LocationDimension expectedLocationDimension = new LocationDimension("mystate", "mydistrict", "myblock", "mypanchayat", LocationStatus.INVALID.name());
        expectedLocationDimension.setAlternateLocation(alternateInvalidLocation);
        when(allLocationDimensions.fetchFor("mydistrict", "myblock", "mypanchayat")).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = locationService.digDeepAndFetchFor("mydistrict", "myblock", "mypanchayat");

        assertEquals(alternateLocation, locationDimension);
    }

    @Test
    public void shouldReturnNullIfTheLocationDoesNotExist() {
        LocationDimension locationDimension = locationService.digDeepAndFetchFor("mydistrict", "myblock", "mypanchayat");

        assertNull(locationDimension);
    }

    @Test
    public void shouldAddOrUpdateLocation() {
        LocationRequest locationRequest = new LocationRequest("s", "d", "b", "p");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());

        locationService.addOrUpdate(new LocationSyncRequest(locationRequest, locationRequest, LocationStatus.VALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions).createOrUpdate(captor.capture());
        LocationDimension actualLocationDimension = captor.getValue();
        assertEquals(new LocationDimension("s", "d", "b", "p", LocationStatus.VALID.name()), actualLocationDimension);
        verify(allSubscribers, never()).findByLocation(any(LocationDimension.class));
        verify(allSubscribers, never()).saveOrUpdateAll(anyList());
    }

    @Test
    public void shouldUpdateLocationStatus() {
        LocationRequest oldLocationRequest = new LocationRequest("s", "d", "b", "p");
        LocationRequest newLocationRequest = new LocationRequest("s1", "d1", "b1", "p1");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());
        LocationDimension expectedOldLocationDimension = new LocationDimension("s", "d", "b", "p", LocationStatus.INVALID.name());
        LocationDimension expectedNewLocationDimension = new LocationDimension("s1", "d1", "b1", "p1", LocationStatus.VALID.name());
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new Subscriber("name", null, null, null, null, expectedOldLocationDimension, null, null, null, new DateTime(lastModifiedTime)));
        when(locationService.digDeepAndFetchFor("d", "b", "p")).thenReturn(expectedOldLocationDimension);
        when(allSubscribers.findByLocation(expectedOldLocationDimension)).thenReturn(subscribers);

        locationService.addOrUpdate(new LocationSyncRequest(oldLocationRequest, newLocationRequest, LocationStatus.INVALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions, times(2)).createOrUpdate(captor.capture());
        List<LocationDimension> actualLocationDimensions = captor.getAllValues();
        assertEquals(expectedNewLocationDimension, actualLocationDimensions.get(0));
        assertEquals(expectedOldLocationDimension, actualLocationDimensions.get(1));
        verifySubscriberLocationUpdate(expectedNewLocationDimension);
    }

    @Test
    public void shouldUpdateLocationOnlyIfLatestRequest() {
        LocationRequest locationRequest = new LocationRequest("s", "d", "b", "p");
        LocationDimension locationDimension = new LocationDimension("s", "d", "b", "p", null);
        locationDimension.setLastModified(new Timestamp(DateTime.now().getMillis()));
        when(allLocationDimensions.fetchFor("d", "b", "p")).thenReturn(locationDimension);

        locationService.addOrUpdate(new LocationSyncRequest(locationRequest, locationRequest, LocationStatus.VALID.name(), DateTime.now().minusDays(1)));

        verify(allLocationDimensions, never()).createOrUpdate(any(LocationDimension.class));
        verify(allSubscribers, never()).findByLocation(any(LocationDimension.class));
        verify(allSubscribers, never()).saveOrUpdateAll(anyList());
    }

    @Test
    public void shouldNotCreateANewLocationIfLocationIsAlreadyPresent() {
        LocationRequest oldLocationRequest = new LocationRequest("s", "d", "b", "p");
        LocationRequest newLocationRequest = new LocationRequest("s1", "d1", "b1", "p1");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());
        LocationDimension expectedOldLocationDimension = new LocationDimension("s", "d", "b", "p", LocationStatus.INVALID.name());
        LocationDimension expectedNewLocationDimension = new LocationDimension("s1", "d1", "b1", "p1", LocationStatus.VALID.name());
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new Subscriber("name", null, null, null, null, expectedOldLocationDimension, null, null, null, new DateTime(lastModifiedTime)));
        when(allLocationDimensions.fetchFor("d", "b", "p")).thenReturn(expectedOldLocationDimension);
        when(allLocationDimensions.fetchFor("d1", "b1", "p1")).thenReturn(expectedNewLocationDimension);
        when(allSubscribers.findByLocation(expectedOldLocationDimension)).thenReturn(subscribers);

        locationService.addOrUpdate(new LocationSyncRequest(oldLocationRequest, newLocationRequest, LocationStatus.INVALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions).createOrUpdate(captor.capture());
        List<LocationDimension> actualLocationDimensions = captor.getAllValues();
        assertEquals(expectedOldLocationDimension, actualLocationDimensions.get(0));
        verifySubscriberLocationUpdate(expectedNewLocationDimension);
    }

    @Test
    public void shouldNotRemapSubscribersIfOldLocationIsNotPresent() {
        LocationRequest oldLocationRequest = new LocationRequest("s", "d", "b", "p");
        LocationRequest newLocationRequest = new LocationRequest("s1", "d1", "b1", "p1");
        Timestamp lastModifiedTime = new Timestamp(DateTime.now().getMillis());
        LocationDimension expectedNewLocationDimension = new LocationDimension("s1", "d1", "b1", "p1", LocationStatus.VALID.name());
        LocationDimension expectedOldLocationDimension = new LocationDimension("s", "d", "b", "p", LocationStatus.INVALID.name());
        expectedOldLocationDimension.setAlternateLocation(expectedNewLocationDimension);
        when(allLocationDimensions.fetchFor("d", "b", "p")).thenReturn(null);
        when(allLocationDimensions.fetchFor("d1", "b1", "p1")).thenReturn(expectedNewLocationDimension);

        locationService.addOrUpdate(new LocationSyncRequest(oldLocationRequest, newLocationRequest, LocationStatus.INVALID.name(), new DateTime(lastModifiedTime)));

        ArgumentCaptor<LocationDimension> captor = ArgumentCaptor.forClass(LocationDimension.class);
        verify(allLocationDimensions).createOrUpdate(captor.capture());
        List<LocationDimension> actualLocationDimensions = captor.getAllValues();
        assertEquals(expectedOldLocationDimension, actualLocationDimensions.get(0));
        verify(allSubscribers, never()).findByLocation(any(LocationDimension.class));
        verify(allSubscribers, never()).saveOrUpdateAll(anyList());
    }

    @Test
    public void shouldFetchLocationIfItExistsAlready() {
        String state = "s";
        String district = "d";
        String block = "b";
        String panchayat = "p";
        LocationDimension expectedLocationDimension = new LocationDimension(state, district, block, panchayat, LocationStatus.VALID.name());
        when(allLocationDimensions.fetchFor(district, block, panchayat)).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = locationService.createAndFetch(new SubscriberLocation(state, district, block, panchayat));

        assertEquals(expectedLocationDimension, locationDimension);
    }

    @Test
    public void shouldFetchAlternateLocationIfExistingLocationIsInvalid() {
        String state = "s";
        String district = "d";
        String block = "b";
        String panchayat = "p";
        LocationDimension alternateLocation = new LocationDimension("s1", "d1", "b1", "p1", LocationStatus.VALID.name());
        LocationDimension expectedLocationDimension = new LocationDimension(state, district, block, panchayat, LocationStatus.INVALID.name());
        expectedLocationDimension.setAlternateLocation(alternateLocation);
        when(allLocationDimensions.fetchFor(district, block, panchayat)).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = locationService.createAndFetch(new SubscriberLocation(state, district, block, panchayat));

        assertEquals(alternateLocation, locationDimension);
    }

    @Test
    public void shouldCreateALocationIfDoesNotExist() {
        String state = "s";
        String district = "d";
        String block = "b";
        String panchayat = "p";
        LocationDimension expectedLocationDimension = new LocationDimension(state, district, block, panchayat, LocationStatus.NOT_VERIFIED.name());
        when(allLocationDimensions.fetchFor(district, block, panchayat)).thenReturn(null);

        LocationDimension locationDimension = locationService.createAndFetch(new SubscriberLocation(state, district, block, panchayat));

        verify(allLocationDimensions).createOrUpdate(expectedLocationDimension);
        assertEquals(expectedLocationDimension, locationDimension);
    }

    @Test
    public void shouldNotCreateOrFetchLocationIfRequestIsNull() {
        LocationDimension locationDimension = locationService.createAndFetch(null);

        assertNull(locationDimension);
    }

    private void verifySubscriberLocationUpdate(LocationDimension newLocation) {
        verify(allSubscribers).saveOrUpdateAll(subscribersCaptor.capture());
        List<Subscriber> actualSubscribersList = subscribersCaptor.getValue();
        assertEquals(1, actualSubscribersList.size());
        assertEquals(newLocation, actualSubscribersList.get(0).getLocationDimension());
    }

}
