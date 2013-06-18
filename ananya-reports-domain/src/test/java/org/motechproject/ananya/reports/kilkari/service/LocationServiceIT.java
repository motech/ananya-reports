package org.motechproject.ananya.reports.kilkari.service;

import org.junit.After;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class LocationServiceIT extends SpringIntegrationTest {

    @Autowired
    private LocationService locationService;

    @After
    public  void tearDown(){
        super.tearDown();
    }

    @Test
    public void shouldFetchLocationDimensionForGivenDistrictBlockAndPanchayat() {
        String district = "District";
        String block = "Block";
        String panchayat="Panchayat";

        LocationDimension existingLocationDimension = new LocationDimension("state", "district", "block", "panchayat", "VALID");
        template.save(existingLocationDimension);
        markForDeletion(existingLocationDimension);

        LocationDimension locationDimension = locationService.digDeepAndFetchFor(district, block, panchayat);
        assertEquals(district,locationDimension.getDistrict());
        assertEquals(block,locationDimension.getBlock());
        assertEquals(panchayat,locationDimension.getPanchayat());
    }
}
