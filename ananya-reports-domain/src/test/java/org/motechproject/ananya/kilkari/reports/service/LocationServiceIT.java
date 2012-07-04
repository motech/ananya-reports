package org.motechproject.ananya.kilkari.reports.service;

import org.junit.After;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.repository.SpringIntegrationTest;
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
        String district = "district";
        String block = "block";
        String panchayat="panchayat";

        LocationDimension existingLocationDimension = new LocationDimension("district", "block", "panchayat");
        template.save(existingLocationDimension);
        markForDeletion(existingLocationDimension);


        LocationDimension locationDimension = locationService.fetchFor(district, block, panchayat);
        assertEquals(district,locationDimension.getDistrict());
        assertEquals(block,locationDimension.getBlock());
        assertEquals(panchayat,locationDimension.getPanchayat());

    }

}
