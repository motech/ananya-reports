package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LocationDimensionTest{

    @Test
    public void shouldConvertLocationDetailsToUpperCase(){
        LocationDimension dimension = new LocationDimension("district", "block", "panchayat","VALID");
        assertEquals("DISTRICT", dimension.getDistrict());
        assertEquals("BLOCK", dimension.getBlock());
        assertEquals("PANCHAYAT", dimension.getPanchayat());
    }

}
