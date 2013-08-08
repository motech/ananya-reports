package org.motechproject.ananya.reports.kilkari.service;

import org.apache.commons.lang.math.NumberUtils;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberCareReportRequest;
import org.motechproject.ananya.reports.kilkari.domain.SubscriberCareRequest;
import org.motechproject.ananya.reports.kilkari.domain.dimension.Subscription;
import org.motechproject.ananya.reports.kilkari.repository.AllChannelDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllDateDimensions;
import org.motechproject.ananya.reports.kilkari.repository.AllSubscriberCareHelpRequest;
import org.motechproject.ananya.reports.kilkari.repository.AllTimeDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubscriberCareHelpService {

    private AllSubscriberCareHelpRequest allSubscriberCareHelpRequest;
    private AllDateDimensions allDateDimensions;
    private AllTimeDimensions allTimeDimensions;
    private AllChannelDimensions allChannelDimensions;

    public SubscriberCareHelpService() {
    }

    @Autowired
    public SubscriberCareHelpService(AllSubscriberCareHelpRequest allSubscriberCareHelpRequest, AllDateDimensions allDateDimensions, AllTimeDimensions allTimeDimensions, AllChannelDimensions allChannelDimensions) {
        this.allSubscriberCareHelpRequest = allSubscriberCareHelpRequest;
        this.allDateDimensions = allDateDimensions;
        this.allTimeDimensions = allTimeDimensions;
        this.allChannelDimensions = allChannelDimensions;
    }

    @Transactional
    public void createHelpRequest(SubscriberCareReportRequest subscriberCareReportRequest) {
        allSubscriberCareHelpRequest.createFor(constructSubscriberCareRequest(subscriberCareReportRequest));
    }

    private SubscriberCareRequest constructSubscriberCareRequest(SubscriberCareReportRequest subscriberCareReportRequest) {
        return new SubscriberCareRequest(NumberUtils.createLong(subscriberCareReportRequest.getMsisdn()),
                subscriberCareReportRequest.getReason(),
                allChannelDimensions.fetchFor(subscriberCareReportRequest.getChannel()),
                allDateDimensions.fetchFor(subscriberCareReportRequest.getCreatedAt()),
                allTimeDimensions.fetchFor(subscriberCareReportRequest.getCreatedAt()));
    }

    @Transactional
    public void deleteFor(Set<Subscription> subscriptions) {
        Set<SubscriberCareRequest> subscriberCareRequestsToDelete = new HashSet<>();
        for (Subscription subscription : subscriptions) {
            List<SubscriberCareRequest> subscriberCareRequests = findByMsisdn(subscription.getMsisdn());
            subscriberCareRequestsToDelete.addAll(subscriberCareRequests);
        }
        allSubscriberCareHelpRequest.deleteAll(subscriberCareRequestsToDelete);
    }

    private List<SubscriberCareRequest> findByMsisdn(Long msisdn) {
        String msisdnString = msisdn != null ? String.valueOf(msisdn) : null;
        return allSubscriberCareHelpRequest.findByMsisdn(msisdnString);
    }
}
