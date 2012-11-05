package org.motechproject.ananya.reports.kilkari.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AllSubscribersTest {
    @Mock
    private DataAccessTemplate template;

    @Test
    public void shouldSaveAllSubscribers() {
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        AllSubscribers allSubscribers = new AllSubscribers(template);

        allSubscribers.saveOrUpdateAll(subscribers);

        verify(template).saveOrUpdateAll(subscribers);
    }
}
