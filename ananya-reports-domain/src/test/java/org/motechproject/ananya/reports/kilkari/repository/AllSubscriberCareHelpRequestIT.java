package org.motechproject.ananya.reports.kilkari.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.reports.kilkari.domain.SubscriberCareRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.ChannelDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.TimeDimension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class AllSubscriberCareHelpRequestIT extends SpringIntegrationTest {

    @Autowired
    private AllSubscriberCareHelpRequest allSubscriberCareHelpRequest;
    @Autowired
    private AllDateDimensions allDateDimensions;
    @Autowired
    private AllTimeDimensions allTimeDimensions;
    @Autowired
    private AllChannelDimensions allChannelDimensions;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(SubscriberCareRequest.class));
    }

    @Test
    public void shouldSaveHelpRequest() {
        Long msisdn = 1234567890L;
        String reason = "HELP";
        String channel = "ivr";
        DateTime now = DateTime.now();
        ChannelDimension channelDimension = allChannelDimensions.fetchFor(channel);
        DateDimension dateDimension = allDateDimensions.fetchFor(now);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(now);
        SubscriberCareRequest subscriberCareRequest = new SubscriberCareRequest(msisdn, reason, channelDimension, dateDimension, timeDimension);

        allSubscriberCareHelpRequest.createFor(subscriberCareRequest);

        List<SubscriberCareRequest> subscriberCareRequests = template.loadAll(SubscriberCareRequest.class);
        assertEquals(1, subscriberCareRequests.size());
        assertEquals(msisdn.toString(), subscriberCareRequest.getMsisdn());
        assertEquals(channelDimension.getChannel(), subscriberCareRequest.getChannelDimension().getChannel());
        assertEquals(reason, subscriberCareRequest.getReason());
        assertEquals(dateDimension, subscriberCareRequest.getDateDimension());
        assertEquals(timeDimension, subscriberCareRequest.getTimeDimension());
    }

    @Test
    public void shouldFindByMsisdn() {
        Long msisdn = 1234567890L;
        String reason = "HELP";
        String channel = "ivr";
        DateTime now = DateTime.now();
        ChannelDimension channelDimension = allChannelDimensions.fetchFor(channel);
        DateDimension dateDimension = allDateDimensions.fetchFor(now);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(now);
        SubscriberCareRequest subscriberCareRequest = new SubscriberCareRequest(msisdn, reason, channelDimension, dateDimension, timeDimension);
        template.save(subscriberCareRequest);

        List<SubscriberCareRequest> actualCareRequests = allSubscriberCareHelpRequest.findByMsisdn(msisdn.toString());

        assertEquals(1, actualCareRequests.size());
        assertEquals(subscriberCareRequest, actualCareRequests.get(0));
    }

    @Test
    public void shouldDeleteAllSubscriberCareRequests() {
        DateTime now = DateTime.now();
        HashSet<SubscriberCareRequest> subscriberCareRequests = new HashSet<>();
        ChannelDimension channelDimension = allChannelDimensions.fetchFor("ivr");
        DateDimension dateDimension = allDateDimensions.fetchFor(now);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(now);
        subscriberCareRequests.add(new SubscriberCareRequest(1234567890L, "reason", channelDimension, dateDimension, timeDimension));
        subscriberCareRequests.add(new SubscriberCareRequest(1234567891L, "reason", channelDimension, dateDimension, timeDimension));
        template.saveOrUpdateAll(subscriberCareRequests);

        allSubscriberCareHelpRequest.deleteAll(subscriberCareRequests);

        assertTrue(template.loadAll(SubscriberCareRequest.class).isEmpty());
    }
}
