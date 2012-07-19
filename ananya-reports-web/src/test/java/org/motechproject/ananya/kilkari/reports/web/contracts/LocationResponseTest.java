package org.motechproject.ananya.kilkari.reports.web.contracts;

import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.web.response.LocationResponse;

import static org.junit.Assert.assertEquals;

public class LocationResponseTest {

    @Test
    public void shouldCreateLocationResponseFromLocationDimension() {
        LocationResponse locationResponse = new LocationResponse(new LocationDimension("mydistrict", "myblock", "mypanchayat"));
        assertEquals("mydistrict", locationResponse.getDistrict());
        assertEquals("myblock", locationResponse.getBlock());
        assertEquals("mypanchayat", locationResponse.getPanchayat());
    }
}
