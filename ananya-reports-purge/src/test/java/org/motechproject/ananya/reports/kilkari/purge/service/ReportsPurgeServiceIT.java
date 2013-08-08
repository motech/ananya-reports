package org.motechproject.ananya.reports.kilkari.purge.service;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.reports.kilkari.domain.SubscriberCareRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.*;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriberCallMeasure;
import org.motechproject.ananya.reports.kilkari.domain.measure.SubscriptionStatusMeasure;
import org.motechproject.ananya.reports.kilkari.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationKilkariReportsContext.xml")
@Transactional
public class ReportsPurgeServiceIT {
    @Autowired
    private AllChannelDimensions allChannelDimensions;
    @Autowired
    private AllDateDimensions allDateDimensions;
    @Autowired
    private AllTimeDimensions allTimeDimensions;
    @Autowired
    private AllSubscriptionPackDimensions allSubscriptionPackDimensions;
    @Autowired
    private ReportsPurgeService reportsPurgeService;
    @Autowired
    private DataAccessTemplate template;

    private Long msisdnToPurge;

    @Before
    @After
    public void setUp() {
        msisdnToPurge = 1234567890L;
        template.deleteAll(template.loadAll(SubscriptionStatusMeasure.class));
        template.deleteAll(template.loadAll(SubscriberCallMeasure.class));
        template.deleteAll(template.loadAll(CampaignScheduleAlertDetails.class));
        template.deleteAll(template.loadAll(Subscription.class));
        template.deleteAll(template.loadAll(Subscriber.class));
        template.deleteAll(template.loadAll(CampaignDimension.class));
    }

    @Test
    public void shouldPurgeSubscriptionDataForAllRelatedSubscriptionsGivenListOfMsisdns() throws IOException {
        setUpData();
        String filePath = setUpFile();

        reportsPurgeService.purgeSubscriptionData(filePath);

        assertTrue(template.loadAll(SubscriberCallMeasure.class).isEmpty());
        assertTrue(template.loadAll(SubscriptionStatusMeasure.class).isEmpty());
        assertTrue(template.loadAll(CampaignScheduleAlertDetails.class).isEmpty());
        assertTrue(template.loadAll(Subscription.class).isEmpty());
        assertTrue(template.loadAll(Subscriber.class).isEmpty());
        assertTrue(template.loadAll(SubscriberCareRequest.class).isEmpty());
    }

    private String setUpFile() throws IOException {
        String nonExistingMsisdn = "111111";
        File tempFile = File.createTempFile("tmp", "txt");
        tempFile.deleteOnExit();
        String filePath = tempFile.getAbsolutePath();
        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("\n invalid msisdn");
        fileWriter.write("\n " + msisdnToPurge.toString());
        fileWriter.write("\n     ");
        fileWriter.write("\n" + nonExistingMsisdn);
        fileWriter.close();
        return filePath;
    }

    private void setUpData() {
        DateTime now = DateTime.now();
        ChannelDimension channelDimension = allChannelDimensions.fetchFor("IVR");
        DateDimension dateDimension = allDateDimensions.fetchFor(now);
        TimeDimension timeDimension = allTimeDimensions.fetchFor(now);
        SubscriptionPackDimension subscriptionPackDimension = allSubscriptionPackDimensions.fetchFor("NANHI_KILKARI");
        CampaignDimension campaignDimension = new CampaignDimension("WEEK38", 64, 38);
        template.save(campaignDimension);
        Subscriber subscriber = new Subscriber("", 0, now, now, channelDimension, null, dateDimension, null, null, DateTime.now());
        template.save(subscriber);
        Subscription subscription1 = new Subscription(msisdnToPurge, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId1", now, now, "ACTIVE", null, null);
        template.save(subscription1);
        Subscription subscription2 = new Subscription(9876543210L, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId2", now, now, "ACTIVE", subscription1, null);
        template.save(subscription2);
        Subscription subscription3 = new Subscription(msisdnToPurge, subscriber, subscriptionPackDimension, channelDimension, null, dateDimension, "subscriptionId3", now, now, "ACTIVE", subscription2, null);
        template.save(subscription3);
        template.save(new SubscriptionStatusMeasure(subscription1, "ACTIVE", 38, null, null, channelDimension, null, subscriptionPackDimension, dateDimension, timeDimension, now));
        template.save(new SubscriptionStatusMeasure(subscription2, "ACTIVE", 38, null, null, channelDimension, null, subscriptionPackDimension, dateDimension, timeDimension, now));
        template.save(new SubscriptionStatusMeasure(subscription3, "ACTIVE", 38, null, null, channelDimension, null, subscriptionPackDimension, dateDimension, timeDimension, now));
        template.save(new SubscriberCallMeasure("NA", 64, 38, null, subscription1, null, subscriptionPackDimension, campaignDimension, dateDimension, timeDimension, dateDimension, timeDimension, "INBOX", "ACTIVE", 7));
        template.save(new SubscriberCallMeasure("ND", 64, 38, null, subscription2, null, subscriptionPackDimension, campaignDimension, dateDimension, timeDimension, dateDimension, timeDimension, "INBOX", "ACTIVE", 7));
        template.save(new SubscriberCallMeasure("SUCCESS", 64, 38, null, subscription3, null, subscriptionPackDimension, campaignDimension, dateDimension, timeDimension, dateDimension, timeDimension, "INBOX", "ACTIVE", 7));
        template.save(new CampaignScheduleAlertDetails(subscription1, campaignDimension, dateDimension, timeDimension));
        template.save(new CampaignScheduleAlertDetails(subscription2, campaignDimension, dateDimension, timeDimension));
        template.save(new CampaignScheduleAlertDetails(subscription3, campaignDimension, dateDimension, timeDimension));
        template.save(new SubscriberCareRequest(msisdnToPurge, "Reason 1", channelDimension, dateDimension, timeDimension));
        template.save(new SubscriberCareRequest(msisdnToPurge, "Reason 1", channelDimension, dateDimension, timeDimension));
        template.save(new SubscriberCareRequest(msisdnToPurge, "Reason 2", channelDimension, dateDimension, timeDimension));
    }
}
