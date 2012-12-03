package org.motechproject.ananya.kilkari.reports.datasource.service;

import com.jaspersoft.jasperserver.api.metadata.jasperreports.service.ReportDataSourceService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriptionStatusMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SubscriptionDetailsDataService implements ReportDataSourceService {

    @Autowired
    private AllSubscriptionStatusMeasure allSubscriptionStatusMeasures;

    private JRDataSource dataSource;

    public SubscriptionDetailsDataService() {
    }

    public SubscriptionDetailsDataService(AllSubscriptionStatusMeasure allSubscriptionStatusMeasures) {
        this.allSubscriptionStatusMeasures = allSubscriptionStatusMeasures;
    }

    //This is to help in designing in iReports
    public static JRDataSource getJRDataSource() {
        SubscriptionDetailsDataService subscriptionsPerPackDataService = new SubscriptionDetailsDataService();
        return subscriptionsPerPackDataService.getDataSource();
    }

    @Override
    public void setReportParameterValues(Map map) {
        map.put(JRParameter.REPORT_DATA_SOURCE, dataSource);
    }

    @Override
    public void closeConnection() {
    }

    public JRDataSource getDataSource() {
        allSubscriptionStatusMeasures.getSubscriptionDetails();
        return dataSource;
    }
}