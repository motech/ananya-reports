package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AllSubscribersIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscribers allSubscribers;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(Subscriber.class));
        template.deleteAll(template.loadAll(LocationDimension.class));

    }

    @Test
    public void shouldCreateANewSubscriber() {
        String name = "name";
        int age = 42;
        DateTime edd = DateTime.now().minusMonths(4);
        DateTime dob = DateTime.now().minusMonths(8);
        ChannelDimension channelDimension = template.loadAll(ChannelDimension.class).get(0);
        DateDimension dateDimension = template.loadAll(DateDimension.class).get(0);
        Subscriber subscriber = new Subscriber(name, age, edd, dob, channelDimension, null, dateDimension, null, null, DateTime.now());

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
        Subscriber subscriber = new Subscriber(name, null, null, null, channelDimension, locationDimension, dateDimension, null, null, DateTime.now());
        template.save(subscriber);

        List<Subscriber> actualSubscribers = allSubscribers.findByLocation(locationDimension);

        assertEquals(1, actualSubscribers.size());
        assertEquals(name, actualSubscribers.get(0).getName());
    }

    @Test
    public void shouldDeleteAllSubscribers() {
        String name = "name";
        ChannelDimension channelDimension = template.loadAll(ChannelDimension.class).get(0);
        DateDimension dateDimension = template.loadAll(DateDimension.class).get(0);
        LocationDimension locationDimension = new LocationDimension("D1", "B1", "P1", "VALID");
        template.save(locationDimension);
        Subscriber subscriber1 = new Subscriber(name, null, null, null, channelDimension, locationDimension, dateDimension, null, null, DateTime.now());
        template.save(subscriber1);
        Subscriber subscriber2 = new Subscriber(name, null, null, null, channelDimension, locationDimension, dateDimension, null, null, DateTime.now());
        template.save(subscriber2);
        HashSet<Subscriber> subscribers = new HashSet<>();
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);

        allSubscribers.delete(subscribers);

        assertEquals(0, template.loadAll(Subscriber.class).size());
    }
}
