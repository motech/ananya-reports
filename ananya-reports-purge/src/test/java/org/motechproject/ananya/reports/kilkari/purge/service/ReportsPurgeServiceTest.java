package org.motechproject.ananya.reports.kilkari.purge.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscriber;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.domain.dimension.SubscriptionPackDimension;
import org.motechproject.ananya.reports.kilkari.service.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportsPurgeServiceTest {
    @Mock
    private SubscriberCallMeasureService subscriberCallMeasureService;
    @Mock
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private CampaignScheduleAlertService campaignScheduleAlertService;
    @Mock
    private SubscriberService subscriberService;

    private ReportsPurgeService reportsPurgeService;
    private File tempFile;
    private String filePath;

    @Before
    public void setUp() throws IOException {
        reportsPurgeService = new ReportsPurgeService(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService, campaignScheduleAlertService, subscriberService);
        tempFile = File.createTempFile("tmp", "txt");
        tempFile.deleteOnExit();
        filePath = tempFile.getAbsolutePath();
    }

    @Test
    public void shouldDeleteMeasuresBeforeDeletingTheDynamicDimensions() throws IOException {
        String msisdn1 = "1234567890";
        String msisdn2 = "1234567891";
        DateTime now = DateTime.now();

        Set<Subscription> subscriptionSet1 = new LinkedHashSet<>();
        Subscription subscription1 = new Subscription(Long.valueOf(msisdn1), new Subscriber(), new SubscriptionPackDimension("NANHI_KILKARI"), null, null, null, "subscriptionId1", now, now, "ACTIVE", null);
        subscriptionSet1.add(subscription1);
        when(subscriptionService.getAllRelatedSubscriptions(msisdn1)).thenReturn(subscriptionSet1);
        Set<Subscription> subscriptionSet2 = new LinkedHashSet<>();
        Subscription subscription2 = new Subscription(Long.valueOf(msisdn2), new Subscriber(), new SubscriptionPackDimension("NANHI_KILKARI"), null, null, null, "subscriptionId2", now, now, "ACTIVE", null);
        Subscription subscription3 = new Subscription(1234567892L, new Subscriber(), new SubscriptionPackDimension("NANHI_KILKARI"), null, null, null, "subscriptionId3", now, now, "ACTIVE", subscription2);
        subscriptionSet1.add(subscription3);
        subscriptionSet1.add(subscription2);
        when(subscriptionService.getAllRelatedSubscriptions(msisdn2)).thenReturn(subscriptionSet2);
        Set<Subscription> expectedSubscriptions = new LinkedHashSet<>();
        expectedSubscriptions.addAll(subscriptionSet1);
        expectedSubscriptions.addAll(subscriptionSet2);

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write(msisdn1);
        fileWriter.write("\n" + msisdn2);
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        InOrder order = inOrder(subscriberCallMeasureService, subscriptionStatusMeasureService, campaignScheduleAlertService, subscriptionService, subscriberService);

        order.verify(subscriptionService).getAllRelatedSubscriptions(msisdn1);
        order.verify(subscriptionService).getAllRelatedSubscriptions(msisdn2);

        verifyOrderedMeasureDeletion(order, subscription1);
        verifyOrderedMeasureDeletion(order, subscription3);
        verifyOrderedMeasureDeletion(order, subscription2);
        order.verify(subscriptionService).deleteAll(expectedSubscriptions);
        order.verify(subscriberService).deleteFor(expectedSubscriptions);

        verifyNoMoreInteractions(subscriberCallMeasureService);
        verifyNoMoreInteractions(subscriptionStatusMeasureService);
        verifyNoMoreInteractions(campaignScheduleAlertService);
        verifyNoMoreInteractions(subscriptionService);
        verifyNoMoreInteractions(subscriberService);
    }

    private void verifyOrderedMeasureDeletion(InOrder order, Subscription subscription) {
        order.verify(subscriberCallMeasureService).deleteFor(subscription);
        order.verify(subscriptionStatusMeasureService).deleteFor(subscription);
        order.verify(campaignScheduleAlertService).deleteFor(subscription);
    }

    @Test
    public void shouldIgnoreBlankLinesWhileReadingFromTheFile() throws IOException {
        String msisdn1 = "1234567890";
        String msisdn2 = "1234567891";

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("    ");
        fileWriter.write("\n" + msisdn1);
        fileWriter.write("\n   ");
        fileWriter.write("\n" + msisdn2);
        fileWriter.write("\n");
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        verify(subscriptionService).getAllRelatedSubscriptions(msisdn1);
        verify(subscriptionService).getAllRelatedSubscriptions(msisdn2);
        verify(subscriptionService, times(2)).getAllRelatedSubscriptions(anyString());
    }

    @Test
    public void shouldTrimMsisdnWhileReadingFromTheFile() throws IOException {
        String msisdn1 = "1234567890";
        String msisdn2 = "1234567891";

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("    " + msisdn1 + "    ");
        fileWriter.write("\n   " + msisdn2);
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        verify(subscriptionService).getAllRelatedSubscriptions(msisdn1);
        verify(subscriptionService).getAllRelatedSubscriptions(msisdn2);
    }
}
