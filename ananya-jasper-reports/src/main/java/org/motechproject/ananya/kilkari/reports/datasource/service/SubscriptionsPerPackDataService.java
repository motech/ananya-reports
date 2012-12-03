package org.motechproject.ananya.kilkari.reports.datasource.service;

import com.jaspersoft.jasperserver.api.metadata.jasperreports.service.ReportDataSourceService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.motechproject.ananya.kilkari.reports.datasource.domain.SubscriptionsPerPack;

import java.util.ArrayList;
import java.util.Map;

public class SubscriptionsPerPackDataService implements ReportDataSourceService{

    private JRDataSource dataSource;

    public SubscriptionsPerPackDataService() {
        ArrayList<SubscriptionsPerPack> subscriptionsPerPacks = new ArrayList<SubscriptionsPerPack>();
        SubscriptionsPerPack small = new SubscriptionsPerPack("small", 10, 20, 20, 30, 40, 50, 1);
        SubscriptionsPerPack medium = new SubscriptionsPerPack("medium", 20, 20, 20, 30, 40, 50, 1);
        SubscriptionsPerPack big = new SubscriptionsPerPack("big", 30, 20, 20, 30, 40, 50, 1);

        subscriptionsPerPacks.add(small);
        subscriptionsPerPacks.add(medium);
        subscriptionsPerPacks.add(big);

        dataSource = new JRBeanCollectionDataSource(subscriptionsPerPacks);
    }

    //This is to help in designing in iReports
    public static JRDataSource getJRDataSource() {
        SubscriptionsPerPackDataService subscriptionsPerPackDataService = new SubscriptionsPerPackDataService();
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
        return dataSource;
    }
}




