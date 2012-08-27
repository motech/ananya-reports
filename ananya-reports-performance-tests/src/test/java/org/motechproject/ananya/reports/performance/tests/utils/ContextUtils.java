package org.motechproject.ananya.reports.performance.tests.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtils {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationReportsPerformanceContext.xml");

    public static BaseConfiguration getConfiguration(){
        return (BaseConfiguration)context.getBean("baseConfiguration");

    }
}
