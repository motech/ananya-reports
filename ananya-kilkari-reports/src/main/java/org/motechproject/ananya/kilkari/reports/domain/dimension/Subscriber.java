package org.motechproject.ananya.kilkari.reports.domain.dimension;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "name")
    private String name;

    @Column(name = "age_of_beneficiary")
    private int ageOfBeneficiary;

    @Column(name = "estimated_date_of_delivery")
    private Date estimatedDateOfDelivery;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private ChannelDimension channelDimension;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationDimension locationDimension;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private TimeDimension timeDimension;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false)
    private OperatorDimension operatorDimension;

    public Subscriber() {
    }

    public Subscriber(String msisdn, String name, int ageOfBeneficiary, DateTime estimatedDateOfDelivery,
                      DateTime dateOfBirth, ChannelDimension channelDimension, LocationDimension locationDimension,
                      TimeDimension timeDimension, OperatorDimension operatorDimension) {
        this.msisdn = msisdn;
        this.name = name;
        this.ageOfBeneficiary = ageOfBeneficiary;
        this.estimatedDateOfDelivery = new Date(estimatedDateOfDelivery.toDate().getTime());
        this.dateOfBirth = new Date(dateOfBirth.toDate().getTime());
        this.channelDimension = channelDimension;
        this.locationDimension = locationDimension;
        this.timeDimension = timeDimension;
        this.operatorDimension = operatorDimension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeOfBeneficiary() {
        return ageOfBeneficiary;
    }

    public void setAgeOfBeneficiary(int ageOfBeneficiary) {
        this.ageOfBeneficiary = ageOfBeneficiary;
    }

    public DateTime getEstimatedDateOfDelivery() {
        return new DateTime(estimatedDateOfDelivery);
    }

    public void setEstimatedDateOfDelivery(DateTime estimatedDateOfDelivery) {
        this.estimatedDateOfDelivery = new Date(estimatedDateOfDelivery.toDate().getTime());
    }

    public DateTime getDateOfBirth() {
        return new DateTime(dateOfBirth);
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = new Date(dateOfBirth.toDate().getTime());
    }

    public ChannelDimension getChannelDimension() {
        return channelDimension;
    }

    public void setChannelDimension(ChannelDimension channelDimension) {
        this.channelDimension = channelDimension;
    }

    public LocationDimension getLocationDimension() {
        return locationDimension;
    }

    public void setLocationDimension(LocationDimension locationDimension) {
        this.locationDimension = locationDimension;
    }

    public TimeDimension getTimeDimension() {
        return timeDimension;
    }

    public void setTimeDimension(TimeDimension timeDimension) {
        this.timeDimension = timeDimension;
    }

    public OperatorDimension getOperatorDimension() {
        return operatorDimension;
    }

    public void setOperatorDimension(OperatorDimension operatorDimension) {
        this.operatorDimension = operatorDimension;
    }
}
