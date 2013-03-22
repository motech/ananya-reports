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

import java.util.List;

import static junit.framework.Assert.assertEquals;

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
        long msisdn = 1234567890L;
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
        assertEquals(new Long(msisdn), subscriberCareRequest.getMsisdn());
        assertEquals(channelDimension.getChannel(), subscriberCareRequest.getChannelDimension().getChannel());
        assertEquals(reason, subscriberCareRequest.getReason());
        assertEquals(dateDimension, subscriberCareRequest.getDateDimension());
        assertEquals(timeDimension, subscriberCareRequest.getTimeDimension());
    }
}
