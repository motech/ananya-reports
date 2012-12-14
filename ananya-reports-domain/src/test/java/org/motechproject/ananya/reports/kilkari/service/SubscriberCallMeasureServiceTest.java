package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailRecordRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.CallDetailsReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.CampaignMessageCallSource;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriberCallMeasure;
import org.motechproject.ananya.reports.kilkari.repository.AllCampaignDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriberCallMeasures;
import org.motechproject.ananya.reports.kilkari.repository.AllTimeDimensions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriberCallMeasureServiceTest {


    private SubscriberCallMeasureService subscriberCallMeasureService;
    @Mock
    private AllSubscriberCallMeasures allSubscriberCallMeasures;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private AllCampaignDimensions allCampaignDimensions;
    @Mock
    private AllDateDimensions allDateDimensions;
    @Mock
    private AllTimeDimensions allTimeDimensions;
    @Mock
    private OperatorService operatorService;

    @Before
    public void setUp() {
        initMocks(this);
        subscriberCallMeasureService = new SubscriberCallMeasureService(allSubscriberCallMeasures, subscriptionService, allCampaignDimensions, allDateDimensions, allTimeDimensions, operatorService);
    }

    @Test
    public void shouldCreateSubscriberCallDetailsForOBD() {
        //Given
        String subscriptionId = "subId";
        String msisdn = "9876543210";
        String campaignId = "1";
        String retryCount = "2";
        String serviceOption = "HELP";
        Integer percentageListened = 50;
        String status = "DNP";
        String startTime = "01-01-2012 01-10-00";
        String endTime = "01-01-2012 01-40-00";
        Integer expectedDurationInPulse = 30;

        DateTime startDateTime = DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss").parseDateTime(startTime);
        DateTime endDateTime = DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss").parseDateTime(endTime);
        CallDetailsReportRequest callDetailsReportRequest = new CallDetailsReportRequest(subscriptionId, msisdn, campaignId, serviceOption, retryCount, status, new CallDetailRecordRequest(startDateTime, endDateTime), CampaignMessageCallSource.OBD.name());

        Subscription mockedSubscription = mock(Subscription.class);
        OperatorDimension mockedOperatorDimension = mock(OperatorDimension.class);
        CampaignDimension mockedCampaignDimension = mock(CampaignDimension.class);
        DateDimension mockedStartDateDimension = mock(DateDimension.class);
        TimeDimension mockedStartTimeDimension = mock(TimeDimension.class);
        DateDimension mockedEndDateDimension = mock(DateDimension.class);
        TimeDimension mockedEndTimeDimension = mock(TimeDimension.class);
        SubscriptionPackDimension mockedSubscriptionPackDimension = mock(SubscriptionPackDimension.class);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        when(allCampaignDimensions.fetchFor(campaignId)).thenReturn(mockedCampaignDimension);
        when(allDateDimensions.fetchFor(startDateTime)).thenReturn(mockedStartDateDimension);
        when(allTimeDimensions.fetchFor(startDateTime)).thenReturn(mockedStartTimeDimension);
        when(allDateDimensions.fetchFor(endDateTime)).thenReturn(mockedEndDateDimension);
        when(allTimeDimensions.fetchFor(endDateTime)).thenReturn(mockedEndTimeDimension);
        when(mockedSubscription.getOperatorDimension()).thenReturn(mockedOperatorDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(mockedSubscriptionPackDimension);
        String expectedSubscriptionStatus = "ACTIVE";
        when(mockedSubscription.getSubscriptionStatus()).thenReturn(expectedSubscriptionStatus);
        when(mockedCampaignDimension.getObdMessageDuration()).thenReturn(3600);
        when(operatorService.fetchDurationInPulse(mockedOperatorDimension, 30 * 60)).thenReturn(30);
        //When
        subscriberCallMeasureService.createSubscriberCallDetails(callDetailsReportRequest);

        //Expected
        verify(subscriptionService).fetchFor(callDetailsReportRequest.getSubscriptionId());
        verify(mockedCampaignDimension, never()).getInboxMessageDuration();

        verify(allCampaignDimensions).fetchFor(campaignId);
        verify(allDateDimensions).fetchFor(startDateTime);
        verify(allTimeDimensions).fetchFor(startDateTime);
        verify(allDateDimensions).fetchFor(endDateTime);
        verify(allTimeDimensions).fetchFor(endDateTime);

        ArgumentCaptor<SubscriberCallMeasure> subscriberCallMeasureCaptor = ArgumentCaptor.forClass(SubscriberCallMeasure.class);
        verify(allSubscriberCallMeasures).createFor(subscriberCallMeasureCaptor.capture());
        SubscriberCallMeasure subscriberCallMeasure = subscriberCallMeasureCaptor.getValue();
        assertEquals(callDetailsReportRequest.getDuration(), subscriberCallMeasure.getDuration());
        assertEquals(callDetailsReportRequest.getServiceOption(), subscriberCallMeasure.getServiceOption());
        assertEquals(mockedSubscription, subscriberCallMeasure.getSubscription());
        assertEquals(mockedOperatorDimension, subscriberCallMeasure.getOperatorDimension());
        assertEquals(mockedSubscriptionPackDimension, subscriberCallMeasure.getSubscriptionPackDimension());
        assertEquals(mockedStartDateDimension, subscriberCallMeasure.getStartDate());
        assertEquals(mockedStartTimeDimension, subscriberCallMeasure.getStartTime());
        assertEquals(mockedEndDateDimension, subscriberCallMeasure.getEndDate());
        assertEquals(mockedEndTimeDimension, subscriberCallMeasure.getEndTime());
        assertEquals(percentageListened, subscriberCallMeasure.getPercentageListened());
        assertEquals(callDetailsReportRequest.getStatus(), subscriberCallMeasure.getCallStatus());
        assertEquals((Integer) 2, subscriberCallMeasure.getRetryCount());
        assertEquals(CampaignMessageCallSource.OBD.name(), subscriberCallMeasure.getCallSource());
        assertEquals(expectedSubscriptionStatus, subscriberCallMeasure.getSubscriptionStatus());
        assertEquals(expectedDurationInPulse, subscriberCallMeasure.getDurationInPulse());
    }

    @Test
    public void shouldCreateSubscriberCallDetailsForInbox() {
        //Given
        String subscriptionId = "subId";
        String msisdn = "9876543210";
        String campaignId = "1";
        String retryCount = "2";
        String serviceOption = "HELP";
        Integer percentageListened = 50;
        String status = "DNP";
        String startTime = "01-01-2012 01-10-00";
        String endTime = "01-01-2012 01-40-00";
        DateTime startDateTime = DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss").parseDateTime(startTime);
        DateTime endDateTime = DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss").parseDateTime(endTime);
        CallDetailsReportRequest callDetailsReportRequest = new CallDetailsReportRequest(subscriptionId, msisdn, campaignId, serviceOption, retryCount, status, new CallDetailRecordRequest(startDateTime, endDateTime), CampaignMessageCallSource.INBOX.name());

        Subscription mockedSubscription = mock(Subscription.class);
        OperatorDimension mockedOperatorDimension = mock(OperatorDimension.class);
        CampaignDimension mockedCampaignDimension = mock(CampaignDimension.class);
        DateDimension mockedStartDateDimension = mock(DateDimension.class);
        TimeDimension mockedStartTimeDimension = mock(TimeDimension.class);
        DateDimension mockedEndDateDimension = mock(DateDimension.class);
        TimeDimension mockedEndTimeDimension = mock(TimeDimension.class);
        SubscriptionPackDimension mockedSubscriptionPackDimension = mock(SubscriptionPackDimension.class);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        when(allCampaignDimensions.fetchFor(campaignId)).thenReturn(mockedCampaignDimension);
        when(allDateDimensions.fetchFor(startDateTime)).thenReturn(mockedStartDateDimension);
        when(allTimeDimensions.fetchFor(startDateTime)).thenReturn(mockedStartTimeDimension);
        when(allDateDimensions.fetchFor(endDateTime)).thenReturn(mockedEndDateDimension);
        when(allTimeDimensions.fetchFor(endDateTime)).thenReturn(mockedEndTimeDimension);
        when(mockedSubscription.getOperatorDimension()).thenReturn(mockedOperatorDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(mockedSubscriptionPackDimension);
        when(mockedCampaignDimension.getInboxMessageDuration()).thenReturn(3600);

        //When
        subscriberCallMeasureService.createSubscriberCallDetails(callDetailsReportRequest);

        //Expected
        verify(subscriptionService).fetchFor(subscriptionId);
        verify(mockedCampaignDimension, never()).getObdMessageDuration();

        verify(allCampaignDimensions).fetchFor(campaignId);
        verify(allDateDimensions).fetchFor(startDateTime);
        verify(allTimeDimensions).fetchFor(startDateTime);
        verify(allDateDimensions).fetchFor(endDateTime);
        verify(allTimeDimensions).fetchFor(endDateTime);

        ArgumentCaptor<SubscriberCallMeasure> subscriberCallMeasureCaptor = ArgumentCaptor.forClass(SubscriberCallMeasure.class);
        verify(allSubscriberCallMeasures).createFor(subscriberCallMeasureCaptor.capture());
        SubscriberCallMeasure subscriberCallMeasure = subscriberCallMeasureCaptor.getValue();
        assertEquals(callDetailsReportRequest.getDuration(), subscriberCallMeasure.getDuration());
        assertEquals(callDetailsReportRequest.getServiceOption(), subscriberCallMeasure.getServiceOption());
        assertEquals(mockedSubscription, subscriberCallMeasure.getSubscription());
        assertEquals(mockedOperatorDimension, subscriberCallMeasure.getOperatorDimension());
        assertEquals(mockedSubscriptionPackDimension, subscriberCallMeasure.getSubscriptionPackDimension());
        assertEquals(mockedStartDateDimension, subscriberCallMeasure.getStartDate());
        assertEquals(mockedStartTimeDimension, subscriberCallMeasure.getStartTime());
        assertEquals(mockedEndDateDimension, subscriberCallMeasure.getEndDate());
        assertEquals(mockedEndTimeDimension, subscriberCallMeasure.getEndTime());
        assertEquals(percentageListened, subscriberCallMeasure.getPercentageListened());
        assertEquals(callDetailsReportRequest.getStatus(), subscriberCallMeasure.getCallStatus());
        assertEquals((Integer) 2, subscriberCallMeasure.getRetryCount());
        assertEquals(CampaignMessageCallSource.INBOX.name(), subscriberCallMeasure.getCallSource());
    }

    @Test
    public void shouldCreateSubscriberCallDetailsWithNullValues() {
        //Given
        String subscriptionId = "subId";
        String msisdn = "9876543210";
        String campaignId = "1";
        String retryCount = null;
        String serviceOption = null;
        Integer percentageListened = 0;
        String status = "DNP";
        String startTime = "01-01-2012 01-10-00";
        DateTime startDateTime = DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss").parseDateTime(startTime);
        CallDetailsReportRequest callDetailsReportRequest = new CallDetailsReportRequest(subscriptionId, msisdn, campaignId, serviceOption, retryCount, status, new CallDetailRecordRequest(startDateTime, startDateTime), CampaignMessageCallSource.OBD.name());

        Subscription mockedSubscription = mock(Subscription.class);
        OperatorDimension mockedOperatorDimension = mock(OperatorDimension.class);
        CampaignDimension mockedCampaignDimension = mock(CampaignDimension.class);
        DateDimension mockedStartDateDimension = mock(DateDimension.class);
        TimeDimension mockedStartTimeDimension = mock(TimeDimension.class);
        SubscriptionPackDimension mockedSubscriptionPackDimension = mock(SubscriptionPackDimension.class);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        when(allCampaignDimensions.fetchFor(campaignId)).thenReturn(mockedCampaignDimension);
        when(allDateDimensions.fetchFor(startDateTime)).thenReturn(mockedStartDateDimension);
        when(allTimeDimensions.fetchFor(startDateTime)).thenReturn(mockedStartTimeDimension);
        when(mockedSubscription.getOperatorDimension()).thenReturn(mockedOperatorDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(mockedSubscriptionPackDimension);
        when(mockedCampaignDimension.getObdMessageDuration()).thenReturn(3600);

        //When
        subscriberCallMeasureService.createSubscriberCallDetails(callDetailsReportRequest);

        //Expected
        verify(subscriptionService).fetchFor(subscriptionId);

        verify(allCampaignDimensions).fetchFor(campaignId);
        verify(allDateDimensions, times(2)).fetchFor(startDateTime);
        verify(allTimeDimensions, times(2)).fetchFor(startDateTime);

        ArgumentCaptor<SubscriberCallMeasure> subscriberCallMeasureCaptor = ArgumentCaptor.forClass(SubscriberCallMeasure.class);
        verify(allSubscriberCallMeasures).createFor(subscriberCallMeasureCaptor.capture());
        SubscriberCallMeasure subscriberCallMeasure = subscriberCallMeasureCaptor.getValue();
        assertEquals(Integer.valueOf(0), subscriberCallMeasure.getDuration());
        assertNull(subscriberCallMeasure.getServiceOption());
        assertEquals(mockedSubscription, subscriberCallMeasure.getSubscription());
        assertEquals(mockedOperatorDimension, subscriberCallMeasure.getOperatorDimension());
        assertEquals(mockedSubscriptionPackDimension, subscriberCallMeasure.getSubscriptionPackDimension());
        assertEquals(mockedStartDateDimension, subscriberCallMeasure.getStartDate());
        assertEquals(mockedStartTimeDimension, subscriberCallMeasure.getStartTime());
        assertEquals(mockedStartDateDimension, subscriberCallMeasure.getEndDate());
        assertEquals(mockedStartTimeDimension, subscriberCallMeasure.getEndTime());
        assertEquals(percentageListened, subscriberCallMeasure.getPercentageListened());
        assertEquals((Integer) 0, subscriberCallMeasure.getPercentageListened());
        assertEquals(callDetailsReportRequest.getStatus(), subscriberCallMeasure.getCallStatus());
        assertNull(subscriberCallMeasure.getRetryCount());
        assertEquals(CampaignMessageCallSource.OBD.name(), subscriberCallMeasure.getCallSource());
    }

    @Test
    public void shouldDeleteAllCallMeasuresForAGivenMsisdn() {
        Long msisdn = 1234L;

        subscriberCallMeasureService.deleteFor(msisdn);

        verify(allSubscriberCallMeasures).deleteFor(msisdn);
    }
}
