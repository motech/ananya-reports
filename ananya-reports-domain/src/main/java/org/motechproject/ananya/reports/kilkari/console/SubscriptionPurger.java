package org.motechproject.ananya.reports.kilkari.console;

import org.motechproject.ananya.reports.kilkari.exception.WrongNumberArgsException;
import org.motechproject.ananya.reports.kilkari.service.ReportsPurgeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SubscriptionPurger {
    private static final String APPLICATION_CONTEXT_XML = "applicationKilkariReportsContext.xml";

    public static void main(String[] args) throws WrongNumberArgsException, IOException {
        validateArguments(args);
        String filePath = args[0];
        ReportsPurgeService reportsPurgeService = getReportsPurgeService();
        reportsPurgeService.purgeSubscriptionData(filePath);
    }

    private static ReportsPurgeService getReportsPurgeService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
        return (ReportsPurgeService) context.getBean("reportsPurgeService");
    }

    private static void validateArguments(String[] args) throws WrongNumberArgsException {
        if (args.length != 1)
            throw new WrongNumberArgsException("Wrong number of arguments. Arguments expected: <file_name>");
    }
}
