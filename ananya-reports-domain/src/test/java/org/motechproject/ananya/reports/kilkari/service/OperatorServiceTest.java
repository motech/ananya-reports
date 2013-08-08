package org.motechproject.ananya.reports.kilkari.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.ananya.reports.kilkari.domain.dimension.OperatorDimension;
import org.motechproject.ananya.reports.kilkari.repository.AllOperatorDimensions;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OperatorServiceTest {

    @Mock
    private AllOperatorDimensions allOperatorDimensions;

    @Test
    public void shouldReturnDurationInPulse() {
        OperatorService operatorService = new OperatorService();
        int actualPulse = operatorService.fetchDurationInPulse(new OperatorDimension("AIRTEL",500,60500), 61);
        assertEquals(2,actualPulse);
    }

}
