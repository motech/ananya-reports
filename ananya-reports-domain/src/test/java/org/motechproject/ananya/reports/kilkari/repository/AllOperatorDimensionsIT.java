package org.motechproject.ananya.reports.kilkari.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.OperatorDimension;
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
        Integer expectedStartPulse =  500;
        Integer expectedEndPulse =  60500;

        OperatorDimension operatorToSave = new OperatorDimension(operator, expectedStartPulse, expectedEndPulse);
        template.save(operatorToSave);

        OperatorDimension operatorDimension = allOperatorDimensions.fetchFor("airTel");
        assertEquals(operator,operatorDimension.getOperator());
        assertEquals(expectedStartPulse,operatorDimension.getStartPulse());
        assertEquals(expectedEndPulse,operatorDimension.getEndPulse());
    }
}
