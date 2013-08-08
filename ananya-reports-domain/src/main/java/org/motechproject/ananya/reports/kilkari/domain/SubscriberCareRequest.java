package org.motechproject.ananya.reports.kilkari.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
    private String msisdn;
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
        this.msisdn = msisdn != null ? String.valueOf(msisdn) : null;
        this.reason = reason;
        this.channelDimension = channelDimension;
        this.dateDimension = dateDimension;
        this.timeDimension = timeDimension;
    }

    public Integer getId() {
        return id;
    }

    public String getMsisdn() {
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

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
