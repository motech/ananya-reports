package org.motechproject.ananya.reports.kilkari.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.repository.AllLocationDimensions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LocationServiceTest {

    @Mock
    private AllLocationDimensions allLocationDimensions;
    private LocationService locationService;

    @Before
    public void setUp() {
        initMocks(this);
        locationService = new LocationService(allLocationDimensions);
    }

    @Test
    public void shouldFetchLocationForDistrictBlockAndPanchayat() {

        LocationDimension expectedLocationDimension = new LocationDimension("mydistrict", "myblock", "mypanchayat");
        when(allLocationDimensions.fetchFor("mydistrict", "myblock", "mypanchayat")).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = locationService.fetchFor("mydistrict", "myblock", "mypanchayat");

        assertEquals(expectedLocationDimension, locationDimension);
    }
}
