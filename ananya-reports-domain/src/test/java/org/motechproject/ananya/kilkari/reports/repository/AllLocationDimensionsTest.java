package org.motechproject.ananya.kilkari.reports.repository;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AllLocationDimensionsTest {

    private AllLocationDimensions allLocationDimensions;

    @Mock
    private DataAccessTemplate dataAccessTemplate;

    @Before
    public void setUp() {
        initMocks(this);
        allLocationDimensions = new AllLocationDimensions(dataAccessTemplate);
    }

    @Test
    public void shouldReturnValidLocationForDistrictBlockAndPanchayat() {
        LocationDimension expectedLocationDimension = new LocationDimension("mydistrict", "myblock", "mypanchayat");
        when(dataAccessTemplate.getUniqueResult(LocationDimension.FIND_BY_DISTRICT_BLOCK_AND_PANCHAYAT, new String[]{"district", "block", "panchayat"}, new String[] {"MYDISTRICT", "MYBLOCK", "MYPANCHAYAT"})).thenReturn(expectedLocationDimension);

        LocationDimension locationDimension = allLocationDimensions.fetchFor("mydistrict", "myblock", "mypanchayat");

        assertEquals(expectedLocationDimension, locationDimension);
    }
}
