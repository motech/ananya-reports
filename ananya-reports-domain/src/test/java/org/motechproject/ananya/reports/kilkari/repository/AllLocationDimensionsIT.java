package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class AllLocationDimensionsIT extends SpringIntegrationTest {

    @Autowired
    private AllLocationDimensions allLocationDimensions;

    @After
    @Before
    public void After() {
        template.deleteAll(template.loadAll(LocationDimension.class));
    }

    @Test
    public void shouldFetchByDistrictBlockPanchayat() {
        LocationDimension expectedDimension = new LocationDimension("d1", "b1", "p1", LocationStatus.NOT_VERIFIED.name(), new Timestamp(DateTime.now().getMillis()));
        allLocationDimensions.createOrUpdate(expectedDimension);

        LocationDimension actualDimension = allLocationDimensions.fetchFor("d1", "b1", "p1");

        assertEquals(expectedDimension, actualDimension);
    }
}
