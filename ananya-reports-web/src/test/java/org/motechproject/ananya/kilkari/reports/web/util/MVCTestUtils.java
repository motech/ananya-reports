package org.motechproject.ananya.kilkari.reports.web.util;

import org.junit.Ignore;
import org.motechproject.ananya.kilkari.reports.web.ExceptionResolver;
import org.motechproject.ananya.kilkari.reports.web.ViewResolver;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.test.web.server.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;

@Ignore
public class MVCTestUtils {

    public static MockMvc mockMvc(Object controller) {
        StandaloneMockMvcBuilder mockMvcBuilder = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(new ViewResolver());

        mockMvcBuilder.setHandlerExceptionResolvers(Arrays.asList(new HandlerExceptionResolver[]{new ExceptionResolver()}));

        return mockMvcBuilder.build();
    }
}
