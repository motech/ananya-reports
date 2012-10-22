package org.motechproject.ananya.reports.kilkari.purge.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.ananya.reports.kilkari.service.SubscriberCallMeasureService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionService;
import org.motechproject.ananya.reports.kilkari.service.SubscriptionStatusMeasureService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ReportsPurgeServiceTest {
    @Mock
    private SubscriberCallMeasureService subscriberCallMeasureService;
    @Mock
    private SubscriptionStatusMeasureService subscriptionStatusMeasureService;
    @Mock
    private SubscriptionService subscriptionService;

    private ReportsPurgeService reportsPurgeService;
    private File tempFile;
    private String filePath;

    @Before
    public void setUp() throws IOException {
        reportsPurgeService = new ReportsPurgeService(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
        tempFile = File.createTempFile("tmp", "txt");
        tempFile.deleteOnExit();
        filePath = tempFile.getAbsolutePath();
    }

    @Test
    public void shouldDeleteMeasuresBeforeDeletingTheDynamicDimensions() throws IOException {
        String msisdn1 = "1234567890";
        String msisdn2 = "1234567891";
        Long msisdn1AsLong = Long.valueOf(msisdn1);
        Long msisdn2AsLong = Long.valueOf(msisdn2);

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write(msisdn1);
        fileWriter.write("\n" + msisdn2);
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        InOrder order = inOrder(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
        order.verify(subscriberCallMeasureService).deleteFor(msisdn1AsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdn1AsLong);
        order.verify(subscriptionService).deleteFor(msisdn1AsLong);

        order.verify(subscriberCallMeasureService).deleteFor(msisdn2AsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdn2AsLong);
        order.verify(subscriptionService).deleteFor(msisdn2AsLong);

        verifyNoMoreInteractions(subscriberCallMeasureService);
        verifyNoMoreInteractions(subscriptionStatusMeasureService);
        verifyNoMoreInteractions(subscriptionService);
    }

    @Test
    public void shouldIngoreBlankLinesWhileReadingFromTheFile() throws IOException {
        String msisdn1 = "1234567890";
        String msisdn2 = "1234567891";
        Long msisdn1AsLong = Long.valueOf(msisdn1);
        Long msisdn2AsLong = Long.valueOf(msisdn2);

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("    ");
        fileWriter.write("\n" + msisdn1);
        fileWriter.write("\n   ");
        fileWriter.write("\n" + msisdn2);
        fileWriter.write("\n");
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        InOrder order = inOrder(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
        order.verify(subscriberCallMeasureService).deleteFor(msisdn1AsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdn1AsLong);
        order.verify(subscriptionService).deleteFor(msisdn1AsLong);

        order.verify(subscriberCallMeasureService).deleteFor(msisdn2AsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdn2AsLong);
        order.verify(subscriptionService).deleteFor(msisdn2AsLong);

        verifyNoMoreInteractions(subscriberCallMeasureService);
        verifyNoMoreInteractions(subscriptionStatusMeasureService);
        verifyNoMoreInteractions(subscriptionService);
    }

    @Test
    public void shouldTrimMsisdnWhileReadingFromTheFile() throws IOException {
        String msisdn1 = "1234567890";
        String invalidMsisdn = "aragorn";
        String msisdn2 = "1234567891";
        Long msisdn1AsLong = Long.valueOf(msisdn1);
        Long msisdn2AsLong = Long.valueOf(msisdn2);

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("    " + msisdn1 + "    ");
        fileWriter.write("\n   " + invalidMsisdn + "    ");
        fileWriter.write("\n   " + msisdn2);
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        InOrder order = inOrder(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
        order.verify(subscriberCallMeasureService).deleteFor(msisdn1AsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdn1AsLong);
        order.verify(subscriptionService).deleteFor(msisdn1AsLong);

        order.verify(subscriberCallMeasureService).deleteFor(msisdn2AsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdn2AsLong);
        order.verify(subscriptionService).deleteFor(msisdn2AsLong);

        verifyNoMoreInteractions(subscriberCallMeasureService);
        verifyNoMoreInteractions(subscriptionStatusMeasureService);
        verifyNoMoreInteractions(subscriptionService);
    }
}
