package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberChangeMsisdnReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberLocation;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriptionStateChangeRequest;
import org.motechproject.ananya.reports.kilkari.domain.MessageCampaignPack;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.reports.kilkari.repository.*;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriptionStatusMeasureServiceTest {
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    @Mock
    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasure;
    @Mock
    private AllChannelDimensions allChannelDimensions;
    @Mock
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    @Mock
    private AllOperatorDimensions allOperatorDimensions;
    @Mock
    private AllSubscribers allSubscribers;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private AllSubscriptions allSubscriptions;
    @Mock
    private AllDateDimensions allDateDimensions;
    @Mock
    private AllTimeDimensions allTimeDimensions;
    @Mock
    private LocationService locationService;

    @Before
    public void setup() {
        initMocks(this);
        subscriptionStatusMeasureService = new SubscriptionStatusMeasureService(allSubscriptionStatusMeasure,
                allChannelDimensions, allSubscriptionPackDimensions, allOperatorDimensions, allSubscribers,
                subscriptionService, allSubscriptions, allDateDimensions, locationService, allTimeDimensions);
    }

    @Test
    public void shouldCreateSubscriptionStatusMeasure() {
        long msisdn = 998L;
        String channel = "IVR";
        String name = "name";
        Integer age = 42;
        String subscriptionId = "sub112";
        String operator = "airtel";
        String subscriptionPack = "NAVJAAT_KILKARI";
        DateTime edd = DateTime.now().minusMonths(4);
        DateTime dob = DateTime.now().minusMonths(8);
        String district = "district";
        String block = "block";
        String panchayat = "panchayat";
        DateTime startDate = DateTime.now();
        String reason = "some reason";
        String oldSubscriptionId = null;
        Integer startWeekNumber = 33;
        DateTime createdAt = new DateTime(2012, 01, 01, 10, 10);

        SubscriberLocation subscriberLocation = new SubscriberLocation(district, block, panchayat);
        SubscriptionReportRequest subscriptionReportRequest = new SubscriptionReportRequest(subscriptionId, channel, msisdn, subscriptionPack
                , name, age, createdAt, "NEW", edd, dob, subscriberLocation, operator, startDate, oldSubscriptionId, reason, startWeekNumber);

        ChannelDimension channelDimension = new ChannelDimension();
        DateDimension dateDimension = new DateDimension();
        LocationDimension locationDimension = new LocationDimension();

        Subscriber subscriber = new Subscriber(name, Integer.valueOf(age), edd, dob, channelDimension, locationDimension, dateDimension, null, startWeekNumber, createdAt);
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension(subscriptionPack);
        final Subscription[] subscriptionCapture = new Subscription[1];

        when(allSubscriptions.findBySubscriptionId(oldSubscriptionId)).thenReturn(null);
        when(allChannelDimensions.fetchFor(channel)).thenReturn(channelDimension);
        when(allSubscriptionPackDimensions.fetchFor(subscriptionPack)).thenReturn(subscriptionPackDimension);
        when(allDateDimensions.fetchFor(new DateTime(subscriptionReportRequest.getCreatedAt()))).thenReturn(dateDimension);
        when(locationService.createAndFetch(subscriberLocation)).thenReturn(locationDimension);
        when(allSubscribers.save(any(Subscriber.class))).thenReturn(subscriber);
        when(subscriptionService.makeFor(any(Subscription.class))).thenAnswer(new Answer<Subscription>() {
            @Override
            public Subscription answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                subscriptionCapture[0] = (Subscription) args[0];
                return subscriptionCapture[0];
            }
        });

        subscriptionStatusMeasureService.createSubscription(subscriptionReportRequest);

        verify(locationService).createAndFetch(subscriberLocation);
        verify(allSubscribers).save(subscriber);

        ArgumentCaptor<SubscriptionStatusMeasure> captor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(captor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = captor.getValue();
        assertEquals(subscriptionId, subscriptionStatusMeasure.getSubscription().getSubscriptionId());
        assertEquals(dateDimension, subscriptionStatusMeasure.getDateDimension());
        assertEquals(reason, subscriptionStatusMeasure.getRemarks());

        Subscription subscription = subscriptionCapture[0];
        assertEquals(new Timestamp(startDate.getMillis()), subscription.getStartDate());
        assertEquals(subscriptionId, subscription.getSubscriptionId());
        assertEquals(MessageCampaignPack.NAVJAAT_KILKARI.name(), subscription.getMessageCampaignPack());
    }

    @Test
    public void shouldCreateSubscriptionStatusMeasureForChangePack() {
        long msisdn = 998L;
        String channel = "IVR";
        String name = "name";
        Integer age = 42;
        String subscriptionId = "sub112";
        String operator = "airtel";
        String subscriptionPack = "NAVJAAT_KILKARI";
        DateTime edd = DateTime.now().minusMonths(4);
        DateTime dob = DateTime.now().minusMonths(8);
        DateTime newEdd = DateTime.now().minusMonths(4);
        DateTime newDob = DateTime.now().minusMonths(8);
        DateTime startDate = DateTime.now();
        String reason = "some reason";
        Integer startWeekNumber = 33;
        Integer newStartWeekNumber = 36;
        DateTime createdAt = new DateTime(2012, 01, 01, 10, 10);

        ChannelDimension channelDimension = new ChannelDimension();
        DateDimension dateDimension = new DateDimension();
        LocationDimension locationDimension = new LocationDimension();
        OperatorDimension operatorDimension = new OperatorDimension(operator, 0, 60000);

        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension(subscriptionPack);

        Subscriber subscriber = new Subscriber(name, Integer.valueOf(age), edd, dob, channelDimension, locationDimension, dateDimension, null, startWeekNumber, createdAt.minusDays(5));
        String oldSubscriptionId = "oldSubscriptionId";
        Subscription oldSubscription = new Subscription(msisdn, subscriber, subscriptionPackDimension, channelDimension, operatorDimension,
                dateDimension, oldSubscriptionId, DateTime.now(), startDate.minusDays(5), "NEW", null);
        SubscriptionReportRequest subscriptionReportRequest = new SubscriptionReportRequest(subscriptionId, channel, msisdn, subscriptionPack, null, null,
                createdAt, "NEW", newEdd, newDob, null, null, startDate, oldSubscriptionId, reason, newStartWeekNumber);

        final Subscription[] subscriptionCapture = new Subscription[1];

        when(allSubscriptions.findBySubscriptionId(oldSubscriptionId)).thenReturn(oldSubscription);
        when(allChannelDimensions.fetchFor(channel)).thenReturn(channelDimension);
        when(allSubscriptionPackDimensions.fetchFor(subscriptionPack)).thenReturn(subscriptionPackDimension);
        when(allDateDimensions.fetchFor(new DateTime(subscriptionReportRequest.getCreatedAt()))).thenReturn(dateDimension);

        when(allSubscribers.save(any(Subscriber.class))).thenReturn(subscriber);

        when(subscriptionService.makeFor(any(Subscription.class))).thenAnswer(new Answer<Subscription>() {
            @Override
            public Subscription answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                subscriptionCapture[0] = (Subscription) args[0];
                return subscriptionCapture[0];
            }
        });

        subscriptionStatusMeasureService.createSubscription(subscriptionReportRequest);

        ArgumentCaptor<Subscriber> subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(allSubscribers).save(subscriberArgumentCaptor.capture());
        Subscriber actualSubscriber = subscriberArgumentCaptor.getValue();
        assertEquals(newEdd, actualSubscriber.getEstimatedDateOfDelivery());
        assertEquals(newDob, actualSubscriber.getDateOfBirth());
        assertEquals(locationDimension, actualSubscriber.getLocationDimension());
        assertEquals(newStartWeekNumber, actualSubscriber.getStartWeekNumber());
        assertEquals(createdAt, new DateTime(actualSubscriber.getLastModifiedTime()));

        ArgumentCaptor<SubscriptionStatusMeasure> captor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(captor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = captor.getValue();
        assertEquals(subscriptionId, subscriptionStatusMeasure.getSubscription().getSubscriptionId());
        assertEquals(dateDimension, subscriptionStatusMeasure.getDateDimension());
        assertEquals(subscriber, subscriptionStatusMeasure.getSubscription().getSubscriber());

        Subscription actualSubscription = subscriptionCapture[0];
        assertEquals(new Timestamp(startDate.getMillis()), actualSubscription.getStartDate());
        assertEquals(subscriptionId, actualSubscription.getSubscriptionId());
        assertEquals(reason, subscriptionStatusMeasure.getRemarks());
        assertEquals(operator, actualSubscription.getOperatorDimension().getOperator());
        assertEquals(MessageCampaignPack.NAVJAAT_KILKARI.name(), actualSubscription.getMessageCampaignPack());
    }

    @Test
    public void shouldRecordChangeMsisdnForNewEarlySubscription() {
        Long oldMsisdn = 1234567890L;
        Long newMsisdn = 9876543210L;
        DateTime now = DateTime.now();
        Subscription subscription = new Subscription();
        subscription.updateMsisdn(oldMsisdn, now.minusDays(2));
        String subscriptionId = "subscriptionId";
        String expectedReason = "some random reason";
        DateDimension expectedDateDimension = new DateDimension();
        TimeDimension expectedTimeDimension = new TimeDimension();
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(subscription);
        when(allDateDimensions.fetchFor(now)).thenReturn(expectedDateDimension);
        when(allTimeDimensions.fetchFor(now)).thenReturn(expectedTimeDimension);

        subscriptionStatusMeasureService.changeMsisdnForNewEarlySubscription(new SubscriberChangeMsisdnReportRequest(subscriptionId, newMsisdn, expectedReason, now));

        ArgumentCaptor<Subscription> captor = ArgumentCaptor.forClass(Subscription.class);
        verify(allSubscriptions).update(captor.capture());
        Subscription actualSubscription = captor.getValue();
        assertEquals(newMsisdn, actualSubscription.getMsisdn());
        assertEquals(now, new DateTime(actualSubscription.getLastModifiedTime().getTime()));

        ArgumentCaptor<SubscriptionStatusMeasure> subscriptionStatusMeasureArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(subscriptionStatusMeasureArgumentCaptor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = subscriptionStatusMeasureArgumentCaptor.getValue();
        assertEquals(subscription, subscriptionStatusMeasure.getSubscription());
        assertEquals(expectedDateDimension, subscriptionStatusMeasure.getDateDimension());
        assertEquals(expectedTimeDimension, subscriptionStatusMeasure.getTimeDimension());
        assertEquals(now.getMillis(), subscriptionStatusMeasure.getLastModifiedTime().getTime());
        assertEquals(expectedReason, subscriptionStatusMeasure.getRemarks());
    }

    @Test
    public void shouldUpdateSubscriptionStatusMeasureOnlyIfRecent() {
        String subscriptionId = "sub123";
        String subscriptionStatus = "ACTIVE";
        String reason = "my own reason";
        String operator = "airtel";
        Integer graceCount = 4;
        DateTime createdAt = new DateTime(2012, 01, 01, 10, 10);
        Timestamp startDate = new Timestamp(createdAt.getMillis());
        Integer weekNumber = 38;
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest(subscriptionId, subscriptionStatus, reason, createdAt, operator, graceCount, weekNumber);

        ChannelDimension channelDimension = new ChannelDimension();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("NAVJAAT_KILKARI");
        DateDimension mockedDateDimension = new DateDimension(new DateTime(2012, 01, 01, 10, 10));
        TimeDimension timeDimension = new TimeDimension(new DateTime(2012, 01, 01, 10, 10));

        Subscription mockedSubscription = mock(Subscription.class);
        when(mockedSubscription.getChannelDimension()).thenReturn(channelDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(subscriptionPackDimension);
        when(mockedSubscription.getDateDimension()).thenReturn(mockedDateDimension);
        when(mockedSubscription.getSubscriptionId()).thenReturn(subscriptionId);
        when(mockedSubscription.getLastModifiedTime()).thenReturn(new Timestamp(createdAt.plusDays(2).getMillis()));
        when(mockedSubscription.getSubscriptionStatus()).thenReturn("NEW");
        Subscriber mockedSubscriber = mock(Subscriber.class);
        when(mockedSubscription.getSubscriber()).thenReturn(mockedSubscriber);
        when(mockedSubscription.getStartDate()).thenReturn(startDate);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        DateDimension dateDimension = new DateDimension(createdAt);
        when(allDateDimensions.fetchFor(any(DateTime.class))).thenReturn(dateDimension);
        when(allTimeDimensions.fetchFor(any(DateTime.class))).thenReturn(timeDimension);
        OperatorDimension operatorDimension = new OperatorDimension(operator, 0, 60000);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);

        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);

        verify(mockedSubscription, never()).updateStatus(any(DateTime.class), anyString());
        verify(allSubscriptions).update(mockedSubscription);

        ArgumentCaptor<SubscriptionStatusMeasure> subscriptionStatusMeasureArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(subscriptionStatusMeasureArgumentCaptor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = subscriptionStatusMeasureArgumentCaptor.getValue();
        Subscription subscription = subscriptionStatusMeasure.getSubscription();

        assertEquals(subscriptionStatus, subscriptionStatusMeasure.getStatus());
        assertEquals(channelDimension, subscriptionStatusMeasure.getChannelDimension());
        assertEquals(operatorDimension, subscriptionStatusMeasure.getOperatorDimension());
        assertEquals(reason, subscriptionStatusMeasure.getRemarks());
        assertEquals(subscriptionPackDimension, subscriptionStatusMeasure.getSubscriptionPackDimension());
        assertEquals(createdAt, new DateTime(subscriptionStatusMeasure.getDateDimension().getDate().getTime()));
        assertEquals(10, (int) subscriptionStatusMeasure.getTimeDimension().getHourOfDay());
        assertEquals(10, (int) subscriptionStatusMeasure.getTimeDimension().getMinuteOfHour());
        assertEquals(subscriptionId, subscription.getSubscriptionId());
        assertEquals(graceCount, subscriptionStatusMeasure.getGraceCount());
        assertEquals(weekNumber, subscriptionStatusMeasure.getWeekNumber());
    }

    @Test
    public void shouldUpdateSubscriptionStatusMeasureOnSubscriptionStateChange() {
        String subscriptionId = "sub123";
        String subscriptionStatus = "PENDING_ACTIVATION";
        String reason = "my own reason";
        String operator = "airtel";
        Integer graceCount = 4;
        DateTime createdAt = new DateTime(2012, 02, 01, 10, 10);
        DateTime startDate = new DateTime(2012, 03, 01, 10, 10);
        Timestamp startDateTimestamp = new Timestamp(startDate.getMillis());
        Integer weekNumber = 38;
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest(subscriptionId, subscriptionStatus, reason, createdAt, operator, graceCount, weekNumber);

        ChannelDimension channelDimension = new ChannelDimension();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("NAVJAAT_KILKARI");
        DateDimension mockedDateDimension = new DateDimension(new DateTime(2012, 01, 01, 10, 10));
        TimeDimension timeDimension = new TimeDimension(new DateTime(2012, 01, 01, 10, 10));

        Subscription mockedSubscription = mock(Subscription.class);
        when(mockedSubscription.getChannelDimension()).thenReturn(channelDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(subscriptionPackDimension);
        when(mockedSubscription.getDateDimension()).thenReturn(mockedDateDimension);
        when(mockedSubscription.getSubscriptionId()).thenReturn(subscriptionId);
        Subscriber mockedSubscriber = mock(Subscriber.class);
        when(mockedSubscription.getSubscriber()).thenReturn(mockedSubscriber);
        when(mockedSubscription.getLastModifiedTime()).thenReturn(new Timestamp(createdAt.minusDays(2).getMillis()));
        when(mockedSubscription.getSubscriptionStatus()).thenReturn("NEW");
        when(mockedSubscription.getStartDate()).thenReturn(startDateTimestamp);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        DateDimension dateDimension = new DateDimension(createdAt);
        when(allDateDimensions.fetchFor(any(DateTime.class))).thenReturn(dateDimension);
        when(allTimeDimensions.fetchFor(any(DateTime.class))).thenReturn(timeDimension);
        OperatorDimension operatorDimension = new OperatorDimension(operator, 0, 60000);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);

        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);

        verify(mockedSubscription).updateStatus(createdAt, subscriptionStatus);
        verify(allSubscriptions).update(mockedSubscription);

        ArgumentCaptor<SubscriptionStatusMeasure> subscriptionStatusMeasureArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(subscriptionStatusMeasureArgumentCaptor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = subscriptionStatusMeasureArgumentCaptor.getValue();
        Subscription subscription = subscriptionStatusMeasure.getSubscription();

        assertEquals(subscriptionStatus, subscriptionStatusMeasure.getStatus());
        assertEquals(channelDimension, subscriptionStatusMeasure.getChannelDimension());
        assertEquals(operatorDimension, subscriptionStatusMeasure.getOperatorDimension());
        assertEquals(reason, subscriptionStatusMeasure.getRemarks());
        assertEquals(subscriptionPackDimension, subscriptionStatusMeasure.getSubscriptionPackDimension());
        assertEquals(createdAt, new DateTime(subscriptionStatusMeasure.getDateDimension().getDate().getTime()));
        assertEquals(10, (int) subscriptionStatusMeasure.getTimeDimension().getHourOfDay());
        assertEquals(10, (int) subscriptionStatusMeasure.getTimeDimension().getMinuteOfHour());
        assertEquals(subscriptionId, subscription.getSubscriptionId());
        assertEquals(graceCount, subscriptionStatusMeasure.getGraceCount());
        assertEquals(weekNumber, subscriptionStatusMeasure.getWeekNumber());
    }

    @Test
    public void shouldRetainOperatorIfOperatorInRequestIsNullDuringUpdate() {
        String subscriptionId = "sub123";
        String subscriptionStatus = "ACTIVE";
        String reason = "my own reason";
        String operator = "airtel";
        Integer graceCount = 4;
        DateTime createdAt = new DateTime(2012, 02, 01, 10, 10);
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest(subscriptionId, subscriptionStatus, reason, createdAt, operator, graceCount, 38);

        ChannelDimension channelDimension = new ChannelDimension();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("NAVJAAT_KILKARI");
        DateDimension mockedDateDimension = new DateDimension(new DateTime(2012, 01, 01, 10, 10));
        OperatorDimension operatorDimension = new OperatorDimension(operator, 0, 60000);

        Subscription mockedSubscription = mock(Subscription.class);
        when(mockedSubscription.getChannelDimension()).thenReturn(channelDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(subscriptionPackDimension);
        when(mockedSubscription.getDateDimension()).thenReturn(mockedDateDimension);
        when(mockedSubscription.getOperatorDimension()).thenReturn(operatorDimension);
        when(mockedSubscription.getSubscriptionId()).thenReturn(subscriptionId);
        when(mockedSubscription.getLastModifiedTime()).thenReturn(new Timestamp(createdAt.minusDays(4).getMillis()));
        Subscriber mockedSubscriber = mock(Subscriber.class);
        when(mockedSubscription.getSubscriber()).thenReturn(mockedSubscriber);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        DateDimension dateDimension = new DateDimension(createdAt);
        TimeDimension timeDimension = new TimeDimension(createdAt);
        when(allDateDimensions.fetchFor(any(DateTime.class))).thenReturn(dateDimension);
        when(allTimeDimensions.fetchFor(any(DateTime.class))).thenReturn(timeDimension);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);

        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);

        verify(mockedSubscriber).setOperatorDimension(operatorDimension);
        verify(mockedSubscription).setOperatorDimension(operatorDimension);

        ArgumentCaptor<SubscriptionStatusMeasure> subscriptionStatusMeasureArgumentCaptor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(subscriptionStatusMeasureArgumentCaptor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = subscriptionStatusMeasureArgumentCaptor.getValue();

        assertEquals(operatorDimension, subscriptionStatusMeasure.getOperatorDimension());
    }

    @Test
    public void shouldDeleteSubscriptionStatusMeasuresForAGivenMsisdn() {
        Long msisdn = 1234L;

        subscriptionStatusMeasureService.deleteFor(msisdn);

        verify(allSubscriptionStatusMeasure).deleteFor(msisdn);
    }
}
