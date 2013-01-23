package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LocationDimensionTest{

    @Test
    public void shouldConvertLocationDetailsToUpperCase(){
        LocationDimension dimension = new LocationDimension("DISTRICT", "block", "panchayat one","VALID");
        assertEquals("District", dimension.getDistrict());
        assertEquals("Block", dimension.getBlock());
        assertEquals("Panchayat One", dimension.getPanchayat());
    }

}
