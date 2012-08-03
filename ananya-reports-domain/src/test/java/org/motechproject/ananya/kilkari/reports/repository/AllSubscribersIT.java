package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.ChannelDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.DateDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class AllSubscribersIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscribers allSubscribers;

    @Test
    public void shouldCreateANewSubscriber() {
        Long msisdn = 998L;
        String name = "name";
        int age = 42;
        DateTime edd = DateTime.now().minusMonths(4);
        DateTime dob = DateTime.now().minusMonths(8);
        ChannelDimension channelDimension = template.loadAll(ChannelDimension.class).get(0);
        DateDimension dateDimension = template.loadAll(DateDimension.class).get(0);
        Subscriber subscriber = new Subscriber(msisdn, name, age, edd, dob, channelDimension, null, dateDimension, null);

        allSubscribers.save(subscriber);

        Subscriber actualSubscriber = template.loadAll(Subscriber.class).get(0);
        assertEquals(msisdn,actualSubscriber.getMsisdn());
        assertEquals(name,actualSubscriber.getName());
    }
}