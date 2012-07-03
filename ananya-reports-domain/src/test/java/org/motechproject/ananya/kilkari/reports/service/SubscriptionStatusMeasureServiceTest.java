package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.internal.SubscriptionStateChangeRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    private AllTimeDimension allTimeDimension;

    @Before
    public void setup(){
        initMocks(this);
        subscriptionStatusMeasureService= new SubscriptionStatusMeasureService(allSubscriptionStatusMeasure,
                allChannelDimensions, allSubscriptionPackDimensions, allOperatorDimensions, allSubscribers,
                subscriptionService, allSubscriptions, allTimeDimension);

    }

    @Test
    public void shouldCreateSubscriptionStatusMeasure(){
        String msisdn = "998";
        String channel = "IVR";
        String subscriptionId = "sub112";
        String operator = "airtel";
        String subscriptionPack = "TWELVE_MONTHS";
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setMsisdn(msisdn);
        subscriptionRequest.setChannel(channel);
        subscriptionRequest.setSubscriptionId(subscriptionId);
        subscriptionRequest.setOperator(operator);
        subscriptionRequest.setPack(subscriptionPack);
        subscriptionRequest.setCreatedAt(new DateTime(2012, 01, 01, 10, 10));

        ChannelDimension channelDimension = new ChannelDimension();
        TimeDimension timeDimension = new TimeDimension();
        OperatorDimension operatorDimension = new OperatorDimension();
        Subscriber subscriber = new Subscriber();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension(subscriptionPack);
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(subscriptionId);

        when(subscriptionService.exists(subscriptionId)).thenReturn(false);
        when(allChannelDimensions.fetchFor(channel)).thenReturn(channelDimension);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);
        when(allSubscriptionPackDimensions.fetchFor(subscriptionPack)).thenReturn(subscriptionPackDimension);
        when(allTimeDimension.fetchFor(new DateTime(subscriptionRequest.getCreatedAt()))).thenReturn(timeDimension);
        when(allSubscribers.save(msisdn, null, 0, null, null, channelDimension, null,
                timeDimension, operatorDimension)).thenReturn(subscriber);
        when(subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, null, timeDimension, subscriptionId)).thenReturn(subscription);
        
        subscriptionStatusMeasureService.createFor(subscriptionRequest);
        
        ArgumentCaptor<SubscriptionStatusMeasure> captor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(captor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = captor.getValue();
        assertEquals(subscriptionId, subscriptionStatusMeasure.getSubscription().getSubscriptionId());
        assertEquals(timeDimension, subscriptionStatusMeasure.getTimeDimension());
        assertEquals(13, subscriptionStatusMeasure.getWeekNumber());
    }

    @Test
    public void shouldUpdateSubscriptionStatusMeasureOnSubscriptionStateChange() {
        SubscriptionStateChangeRequest subscriptionStateChangeRequest = new SubscriptionStateChangeRequest();
        String subscriptionId = "sub123";
        String subscriptionStatus = "ACTIVE";
        String reason = "my own reason";
        DateTime createdAt = new DateTime(2012, 02, 01, 10, 10);
        subscriptionStateChangeRequest.setSubscriptionId(subscriptionId);
        subscriptionStateChangeRequest.setSubscriptionStatus(subscriptionStatus);
        subscriptionStateChangeRequest.setCreatedAt(createdAt);
        subscriptionStateChangeRequest.setReason(reason);

        ChannelDimension channelDimension = new ChannelDimension();
        OperatorDimension operatorDimension = new OperatorDimension();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension("TWELVE_MONTHS");
        TimeDimension mockedTimeDimension = new TimeDimension(new DateTime(2012, 01, 01, 10, 10));

        Subscription mockedSubscription = mock(Subscription.class);
        when(mockedSubscription.getChannelDimension()).thenReturn(channelDimension);
        when(mockedSubscription.getOperatorDimension()).thenReturn(operatorDimension);
        when(mockedSubscription.getSubscriptionPackDimension()).thenReturn(subscriptionPackDimension);
        when(mockedSubscription.getTimeDimension()).thenReturn(mockedTimeDimension);
        when(mockedSubscription.getSubscriptionId()).thenReturn(subscriptionId);

        when(subscriptionService.fetchFor(subscriptionId)).thenReturn(mockedSubscription);
        TimeDimension timeDimension = new TimeDimension(createdAt);
        when(allTimeDimension.fetchFor(any(DateTime.class))).thenReturn(timeDimension);

        subscriptionStatusMeasureService.update(subscriptionStateChangeRequest);

        ArgumentCaptor<SubscriptionStatusMeasure> captor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(captor.capture());
        SubscriptionStatusMeasure subscriptionStatusMeasure = captor.getValue();
        Subscription subscription = subscriptionStatusMeasure.getSubscription();

        assertEquals(subscriptionStatus, subscriptionStatusMeasure.getStatus());
        assertEquals(17, subscriptionStatusMeasure.getWeekNumber());
        assertEquals(channelDimension, subscriptionStatusMeasure.getChannelDimension());
        assertEquals(operatorDimension, subscriptionStatusMeasure.getOperatorDimension());
        assertEquals(reason, subscriptionStatusMeasure.getRemarks());
        assertEquals(subscriptionPackDimension, subscriptionStatusMeasure.getSubscriptionPackDimension());
        assertEquals(createdAt, new DateTime(subscriptionStatusMeasure.getTimeDimension().getDate().getTime()));
        assertEquals(subscriptionId, subscription.getSubscriptionId());
    }
}
