package org.motechproject.ananya.kilkari.reports.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.ananya.kilkari.reports.datasource.service.SubscriptionDetailsDataService;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptionStatusMeasure;

import static org.mockito.Mockito.verify;

@RunWith(value = MockitoJUnitRunner.class)
public class SubscriptionDetailsDataServiceTest {
    @Mock
    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasures;

    private SubscriptionDetailsDataService subscriptionDetailsDataService;

    @Test
    public void shouldGetSubscriptionDetails() {
        subscriptionDetailsDataService = new SubscriptionDetailsDataService(allSubscriptionStatusMeasures);

        subscriptionDetailsDataService.getDataSource();

        verify(allSubscriptionStatusMeasures).getSubscriptionDetails();
    }
}
