package org.motechproject.ananya.reports.kilkari.domain;


import org.motechproject.ananya.reports.kilkari.domain.dimension.ChannelDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.DateDimension;
import org.motechproject.ananya.reports.kilkari.domain.dimension.TimeDimension;

import javax.persistence.*;

@Entity
@Table(name = "subscriber_care_requests")
public class SubscriberCareRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "msisdn")
    private Long msisdn;
    @Column(name = "reason")
    private String reason;
    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private ChannelDimension channelDimension;
    @ManyToOne
    @JoinColumn(name = "date_id", nullable = false)
    private DateDimension dateDimension;
    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private TimeDimension timeDimension;

    public SubscriberCareRequest() {
    }

    public SubscriberCareRequest(Long msisdn, String reason, ChannelDimension channelDimension, DateDimension dateDimension, TimeDimension timeDimension) {
        this.msisdn = msisdn;
        this.reason = reason;
        this.channelDimension = channelDimension;
        this.dateDimension = dateDimension;
        this.timeDimension = timeDimension;
    }

    public Integer getId() {
        return id;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public String getReason() {
        return reason;
    }

    public ChannelDimension getChannelDimension() {
        return channelDimension;
    }

    public DateDimension getDateDimension() {
        return dateDimension;
    }

    public TimeDimension getTimeDimension() {
        return timeDimension;
    }
}
