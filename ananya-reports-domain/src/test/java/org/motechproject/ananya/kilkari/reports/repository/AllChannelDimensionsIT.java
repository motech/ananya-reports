package org.motechproject.ananya.kilkari.reports.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ananya.kilkari.reports.domain.dimension.ChannelDimension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AllChannelDimensionsIT extends SpringIntegrationTest {
    @Autowired
    private AllChannelDimensions allChannelDimensions;

    @After @Before
    public void After() {
        template.deleteAll(template.loadAll(ChannelDimension.class));
    }

    @Test
    public void shouldFetchByChannelName() {
        String channel = "IVR";
        template.save(new ChannelDimension(channel));
        ChannelDimension channelDimension = allChannelDimensions.fetchFor("IVr");
        assertEquals(channel,channelDimension.getChannel());
    }
}
