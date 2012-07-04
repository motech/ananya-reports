package org.motechproject.ananya.kilkari.reports.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.OperatorDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllOperatorDimensionsIT extends SpringIntegrationTest{

    @Autowired
    private AllOperatorDimensions allOperatorDimensions;

    @After
    @Before
    public void After() {
        template.deleteAll(template.loadAll(OperatorDimension.class));
    }

    @Test
    public void shouldFetchByOperatorName() {
        String operator = "Airtel";
        template.save(new OperatorDimension(operator));
        OperatorDimension operatorDimension = allOperatorDimensions.fetchFor("airTel");
        assertEquals(operator,operatorDimension.getOperator());
    }
}
