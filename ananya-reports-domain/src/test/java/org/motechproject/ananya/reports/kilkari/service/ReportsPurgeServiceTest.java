package org.motechproject.ananya.reports.kilkari.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.Mockito.inOrder;

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
        filePath = tempFile.getAbsolutePath();
    }

    @Test
    public void shouldDeleteMeasuresBeforeDeletingTheDynamicDimensions() throws IOException {
        String msisdn = "1234";
        Long msisdnAsLong = Long.valueOf(msisdn);
        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write(msisdn);
        fileWriter.close();

        reportsPurgeService.purgeSubscriptionData(filePath);

        InOrder order = inOrder(subscriberCallMeasureService, subscriptionStatusMeasureService, subscriptionService);
        order.verify(subscriberCallMeasureService).deleteFor(msisdnAsLong);
        order.verify(subscriptionStatusMeasureService).deleteFor(msisdnAsLong);
        order.verify(subscriptionService).deleteFor(msisdnAsLong);
    }
}
