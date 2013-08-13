package org.motechproject.ananya.reports.kilkari.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.reports.kilkari.contract.request.CampaignChangeReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.MessageCampaignPack;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptions;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriptionServiceTest {
    @Mock
    private AllSubscriptions allSubscriptions;
    @Mock
    private SubscriberService subscriberService;
    private SubscriptionService subscriptionService;

    @Before
    public void setup() {
        initMocks(this);
        subscriptionService = new SubscriptionService(allSubscriptions, subscriberService);
    }

    @Test
    public void shouldMakeASubscription() {
        Subscription subscription = mock(Subscription.class);

        subscriptionService.makeFor(subscription);

        verify(allSubscriptions).save(subscription);
    }

    @Test
    public void shouldFetchASubscriptionByID() {
        String subscriptionId = "abcd1234";

        subscriptionService.fetchFor(subscriptionId);

        verify(allSubscriptions).findBySubscriptionId(subscriptionId);
    }

    @Test
    public void shouldFindSubscriptionsByMsisdn() {
        Long msisdn = 1234567890L;
        ArrayList<Subscription> expectedSubscriptionList = new ArrayList<>();
        when(allSubscriptions.findByMsisdn(msisdn)).thenReturn(expectedSubscriptionList);

        List<Subscription> actualSubscriptionList = subscriptionService.findByMsisdn(msisdn.toString());

        assertEquals(expectedSubscriptionList, actualSubscriptionList);
    }

    @Test
    public void shouldIgnoreInvalidMsisdnStringWhenFindingSubscriptionsByMsisdn() {
        String msisdn = "aragorn";

        List<Subscription> actualSubscriptionList = subscriptionService.findByMsisdn(msisdn);

        verify(allSubscriptions, never()).findByMsisdn(anyLong());
        assertTrue(actualSubscriptionList.isEmpty());
    }

    @Test
    public void shouldUpdateTheLastScheduledDateOnTheSubscription() {
        Subscription expectedSubscription = new Subscription();
        String subscriptionId = "sub";
        DateTime now = DateTime.now();
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(expectedSubscription);

        subscriptionService.updateLastScheduledMessageDate(subscriptionId, now);

        ArgumentCaptor<Subscription> captor = ArgumentCaptor.forClass(Subscription.class);
        verify(allSubscriptions).update(captor.capture());
        Subscription actualSubscription = captor.getValue();
        assertEquals(now.getMillis(), actualSubscription.getLastScheduledMessageDate().getTime());
    }

    @Test
    public void shouldUpdateMessageCampaignPackForASubscription() {
        String subscriptionId = "subscriptionId";
        DateTime createdAt = DateTime.now();
        CampaignChangeReportRequest request = new CampaignChangeReportRequest(MessageCampaignPack.MISCARRIAGE.name(), createdAt);
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(new Subscription());

        subscriptionService.updateMessageCampaign(request, subscriptionId);

        ArgumentCaptor<Subscription> subscriptionArgumentCaptor = ArgumentCaptor.forClass(Subscription.class);
        verify(allSubscriptions).update(subscriptionArgumentCaptor.capture());
        Subscription actualSubscription = subscriptionArgumentCaptor.getValue();
        assertEquals(MessageCampaignPack.MISCARRIAGE.name(), actualSubscription.getMessageCampaignPack());
        assertEquals(createdAt, new DateTime(actualSubscription.getLastModifiedTime()));
    }

    @Test
    public void shouldNotUpdateMessageCampaignPackIfSubscriptionDoesNotExist() {
        String subscriptionId = "subscriptionId";
        when(allSubscriptions.findBySubscriptionId(subscriptionId)).thenReturn(null);

        subscriptionService.updateMessageCampaign(new CampaignChangeReportRequest(MessageCampaignPack.INFANT_DEATH.name(), DateTime.now()), subscriptionId);

        verify(allSubscriptions, never()).update(any(Subscription.class));
    }

    @Test
    public void shouldGetAllRelatedSubscriptionsGivenAnMsisdn() {
        String msisdn = "1234567890";
        Long msisdnAsLong = Long.valueOf(msisdn);
        List<Subscription> subscriptions = Arrays.asList(new Subscription());
        when(allSubscriptions.findByMsisdn(msisdnAsLong)).thenReturn(subscriptions);
        Set<Subscription> expectedRelatedSubscriptions = new LinkedHashSet<>();
        expectedRelatedSubscriptions.add(new Subscription());
        when(allSubscriptions.getAllRelatedSubscriptions(subscriptions)).thenReturn(expectedRelatedSubscriptions);

        Set<Subscription> actualRelatedSubscriptions = subscriptionService.getAllRelatedSubscriptions(msisdn);

        verify(allSubscriptions).findByMsisdn(msisdnAsLong);
        verify(allSubscriptions).getAllRelatedSubscriptions(subscriptions);
        assertEquals(expectedRelatedSubscriptions, actualRelatedSubscriptions);
    }

    @Test
    public void shouldDeleteGivenSetOfSubscriptions() {
        HashSet<Subscription> subscriptions = new HashSet<>();
        subscriptions.add(new Subscription());

        subscriptionService.deleteAll(subscriptions);

        verify(allSubscriptions).deleteAll(subscriptions);
    }
}
