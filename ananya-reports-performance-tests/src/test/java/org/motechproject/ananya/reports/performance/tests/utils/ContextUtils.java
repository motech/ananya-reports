package org.motechproject.ananya.reports.performance.tests.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtils {

    private static ClassPathXmlApplicationContext context;

    static {
        try {
            context = new ClassPathXmlApplicationContext("applicationReportsPerformanceContext.xml");
        } catch (Exception e) {
            System.err.println("Error while initializing spring context: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static BaseConfiguration getConfiguration(){
        return (BaseConfiguration)context.getBean("baseConfiguration");
    }

}
