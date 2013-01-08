package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignScheduleAlertRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.repository.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CampaignScheduleAlertServiceTest {
    @Mock
    private AllCampaignScheduleAlerts allCampaignScheduleAlerts;
    @Mock
    private AllSubscriptions allSubscriptions;
    @Mock
    private AllDateDimensions allDateDimensions;
    @Mock
    private AllTimeDimensions allTimeDimensions;
    @Mock
    private AllCampaignDimensions allCampaignDimensions;
    @Mock
    private SubscriptionService subscriptionService;

    private CampaignScheduleAlertService campaignScheduleAlertService;

    @Before
    public void setUp() throws Exception {
        campaignScheduleAlertService = new CampaignScheduleAlertService(allCampaignScheduleAlerts, allSubscriptions,
                allDateDimensions, allTimeDimensions, allCampaignDimensions, subscriptionService);
    }

    @Test
    public void shouldSaveCampaignScheduleDetails() throws Exception {
        DateTime lastScheduledDate = DateTime.now();
        String subscriptionId = "sid";
        CampaignScheduleAlertRequest scheduleAlertRequest = new CampaignScheduleAlertRequest(subscriptionId, "cid", lastScheduledDate);
        Subscription subscription = new Subscription();
        CampaignDimension campaignDimension = new CampaignDimension();
        DateDimension dateDimension = new DateDimension();
        TimeDimension timeDimension = new TimeDimension();
        when(allSubscriptions.findBySubscriptionId(scheduleAlertRequest.getSubscriptionId())).thenReturn(subscription);
        when(allCampaignDimensions.fetchFor(scheduleAlertRequest.getCampaignId())).thenReturn(campaignDimension);
        when(allDateDimensions.fetchFor(scheduleAlertRequest.getScheduledTime())).thenReturn(dateDimension);
        when(allTimeDimensions.fetchFor(scheduleAlertRequest.getScheduledTime())).thenReturn(timeDimension);

        campaignScheduleAlertService.createCampaignScheduleAlert(
                scheduleAlertRequest);

        ArgumentCaptor<CampaignScheduleAlertDetails> captor = ArgumentCaptor.forClass(CampaignScheduleAlertDetails.class);
        verify(allCampaignScheduleAlerts).save(captor.capture());
        CampaignScheduleAlertDetails campaignScheduleAlertDetails = captor.getValue();
        assertEquals(subscription, campaignScheduleAlertDetails.getSubscription());
        assertEquals(campaignDimension, campaignScheduleAlertDetails.getCampaignId());
        assertEquals(dateDimension, campaignScheduleAlertDetails.getDateId());
        assertEquals(timeDimension, campaignScheduleAlertDetails.getTimeId());

        verify(subscriptionService).updateLastScheduledMessageDate(subscriptionId, lastScheduledDate);
    }
}