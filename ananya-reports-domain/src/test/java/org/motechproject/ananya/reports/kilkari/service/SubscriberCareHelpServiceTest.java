package org.motechproject.ananya.reports.kilkari.service;


import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberCareReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.SubscriberCareRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.ChannelDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.TimeDimension;
import org.motechproject.ananya.reports.kilkari.repository.AllChannelDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriberCareHelpRequest;
import org.motechproject.ananya.reports.kilkari.repository.AllTimeDimensions;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriberCareHelpServiceTest {

    @Mock
    AllSubscriberCareHelpRequest allSubscriberCareHelpRequest;
    @Mock
    AllTimeDimensions allTimeDimensions;
    @Mock
    AllDateDimensions allDateDimensions;
    @Mock
    AllChannelDimensions allChannelDimensions;
    private SubscriberCareHelpService subscriberCareHelpService;

    @Before
    public void setUp() {
        initMocks(this);
        subscriberCareHelpService = new SubscriberCareHelpService(allSubscriberCareHelpRequest, allDateDimensions, allTimeDimensions, allChannelDimensions);
    }

    @Test
    public void shouldCreateSubscriberCareHelpRequest() {
        DateTime now = DateTime.now();
        String reason = "HELP";
        String channel = "channel";
        SubscriberCareReportRequest subscriberCareReportRequest = new SubscriberCareReportRequest("1234567890", reason, channel, now);
        TimeDimension expectedTimeDimension = new TimeDimension(DateTime.now());
        when(allTimeDimensions.fetchFor(now)).thenReturn(expectedTimeDimension);
        DateDimension expectedDateDimension = new DateDimension(DateTime.now());
        when(allDateDimensions.fetchFor(now)).thenReturn(expectedDateDimension);
        ChannelDimension channelDimension = new ChannelDimension();
        when(allChannelDimensions.fetchFor(channel)).thenReturn(channelDimension);

        subscriberCareHelpService.createHelpRequest(subscriberCareReportRequest);

        ArgumentCaptor<SubscriberCareRequest> subscriberCareRequestArgumentCaptor = ArgumentCaptor.forClass(SubscriberCareRequest.class);
        verify(allSubscriberCareHelpRequest).createFor(subscriberCareRequestArgumentCaptor.capture());
        SubscriberCareRequest actualRequest = subscriberCareRequestArgumentCaptor.getValue();
        assertEquals(new Long(1234567890), actualRequest.getMsisdn());
        assertEquals(reason, actualRequest.getReason());
        assertEquals(channelDimension, actualRequest.getChannelDimension());
        assertEquals(expectedDateDimension, actualRequest.getDateDimension());
        assertEquals(expectedTimeDimension, actualRequest.getTimeDimension());
    }
}
