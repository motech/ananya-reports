package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.ChannelDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AllSubscribersIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscribers allSubscribers;

    @Test
    public void shouldCreateANewSubscriber() {
        String name = "name";
        int age = 42;
        DateTime edd = DateTime.now().minusMonths(4);
        DateTime dob = DateTime.now().minusMonths(8);
        ChannelDimension channelDimension = template.loadAll(ChannelDimension.class).get(0);
        DateDimension dateDimension = template.loadAll(DateDimension.class).get(0);
        Subscriber subscriber = new Subscriber(name, age, edd, dob, channelDimension, null, dateDimension, null);

        allSubscribers.save(subscriber);

        Subscriber actualSubscriber = template.loadAll(Subscriber.class).get(0);
        assertEquals(name, actualSubscriber.getName());
        assertEquals((Integer) age, actualSubscriber.getAgeOfBeneficiary());
    }

    @Test
    public void shouldFindAllSubscribersForAGivenLocation() {
        String name = "name";
        ChannelDimension channelDimension = template.loadAll(ChannelDimension.class).get(0);
        DateDimension dateDimension = template.loadAll(DateDimension.class).get(0);
        LocationDimension locationDimension = new LocationDimension("D1", "B1", "P1", "VALID");
        template.save(locationDimension);
        Subscriber subscriber = new Subscriber(name, null, null, null, channelDimension, locationDimension, dateDimension, null);
        template.save(subscriber);

        List<Subscriber> actualSubscribers = allSubscribers.findByLocation(locationDimension);

        assertEquals(1, actualSubscribers.size());
        assertEquals(name, actualSubscribers.get(0).getName());
    }
}
