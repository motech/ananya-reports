package org.motechproject.ananya.kilkari.reports.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.ChannelDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.OperatorDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;

import static org.junit.Assert.assertEquals;

public class AllOperatorDimensionsTest extends SpringIntegrationTest{

    @Autowired
    private AllOperatorDimensions allOperatorDimensions;

    @After
    @Before
    public void After() {
        template.deleteAll(template.loadAll(OperatorDimension.class));
    }

    @Test
    public void shouldFetchByChannelName() {
        String operator = "Airtel";
        template.save(new OperatorDimension(operator));
        OperatorDimension operatorDimension = allOperatorDimensions.fetchFor(operator);
        assertEquals(operator,operatorDimension.getOperator());
    }
}
