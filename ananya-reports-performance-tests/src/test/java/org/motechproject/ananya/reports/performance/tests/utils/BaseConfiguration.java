package org.motechproject.ananya.reports.performance.tests.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class BaseConfiguration {

    @Autowired
    private Properties performanceProperties;

    public Properties getPerformanceProperties() {
        return performanceProperties;
    }

    public String baseUrl(){
        return performanceProperties.getProperty("baseurl");
    }
}
