package org.motechproject.ananya.kilkari.reports.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.kilkari.internal.SubscriptionRequest;
import org.motechproject.ananya.kilkari.reports.domain.dimension.*;
import org.motechproject.ananya.kilkari.reports.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.kilkari.reports.repository.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
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
        String subscriptionPack = "PCK1";
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setMsisdn(msisdn);
        subscriptionRequest.setChannel(channel);
        subscriptionRequest.setSubscriptionId(subscriptionId);
        subscriptionRequest.setOperator(operator);
        subscriptionRequest.setPack(subscriptionPack);

        ChannelDimension channelDimension = new ChannelDimension();
        TimeDimension timeDimension = new TimeDimension();
        OperatorDimension operatorDimension = new OperatorDimension();
        Subscriber subscriber = new Subscriber();
        SubscriptionPackDimension subscriptionPackDimension = new SubscriptionPackDimension();
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(subscriptionId);

        when(subscriptionService.exists(subscriptionId)).thenReturn(false);
        when(allChannelDimensions.fetchFor(channel)).thenReturn(channelDimension);
        when(allOperatorDimensions.fetchFor(operator)).thenReturn(operatorDimension);
        when(allSubscriptionPackDimensions.fetchFor(subscriptionPack)).thenReturn(subscriptionPackDimension);
        when(allTimeDimension.fetchFor(any(DateTime.class))).thenReturn(timeDimension);
        when(allSubscribers.save(msisdn, null, 0, null, null, channelDimension, null,
                timeDimension, operatorDimension)).thenReturn(subscriber);
        when(subscriptionService.makeFor(subscriber, subscriptionPackDimension, channelDimension,
                operatorDimension, null, timeDimension, subscriptionId)).thenReturn(subscription);
        
        subscriptionStatusMeasureService.createFor(subscriptionRequest);
        
        ArgumentCaptor<SubscriptionStatusMeasure> captor = ArgumentCaptor.forClass(SubscriptionStatusMeasure.class);
        verify(allSubscriptionStatusMeasure).add(captor.capture());
        SubscriptionStatusMeasure value = captor.getValue();
        assertEquals(subscriptionId, value.getSubscription().getSubscriptionId());
    }
}
