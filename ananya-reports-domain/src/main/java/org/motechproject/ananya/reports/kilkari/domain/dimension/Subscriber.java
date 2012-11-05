package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age_of_beneficiary")
    private Integer ageOfBeneficiary;

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
    private DateDimension dateDimension;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorDimension operatorDimension;

    public Subscriber() {
    }

    public Subscriber(String name, Integer ageOfBeneficiary, DateTime estimatedDateOfDelivery,
                      DateTime dateOfBirth, ChannelDimension channelDimension, LocationDimension locationDimension,
                      DateDimension dateDimension, OperatorDimension operatorDimension) {
        this.name = name;
        this.ageOfBeneficiary = ageOfBeneficiary;
        this.estimatedDateOfDelivery = convertToDate(estimatedDateOfDelivery);
        this.dateOfBirth = convertToDate(dateOfBirth);
        this.channelDimension = channelDimension;
        this.locationDimension = locationDimension;
        this.dateDimension = dateDimension;
        this.operatorDimension = operatorDimension;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAgeOfBeneficiary() {
        return ageOfBeneficiary;
    }

    public DateTime getEstimatedDateOfDelivery() {
        return convertToDateTime(estimatedDateOfDelivery);
    }

    public DateTime getDateOfBirth() {
        return convertToDateTime(dateOfBirth);
    }

    public LocationDimension getLocationDimension() {
        return locationDimension;
    }

    public void setOperatorDimension(OperatorDimension operatorDimension) {
        this.operatorDimension = operatorDimension;
    }

    public void setLocationDimension(LocationDimension locationDimension) {
        this.locationDimension = locationDimension;
    }

    public DateDimension getDateDimension() {
        return dateDimension;
    }

    public void updateWith(SubscriberReportRequest subscriberReportRequest, LocationDimension locationDimension, DateDimension dateDimension) {
        ageOfBeneficiary = subscriberReportRequest.getBeneficiaryAge();
        name = subscriberReportRequest.getBeneficiaryName();
        this.dateDimension = dateDimension;
        this.locationDimension = locationDimension;
    }

    private Date convertToDate(DateTime dateTime) {
        return dateTime != null ? new Date(dateTime.toDate().getTime()) : null;
    }

    private DateTime convertToDateTime(Date date) {
        return date != null ? new DateTime(date) : null;
    }

    public void updateWithEddDob(DateTime edd, DateTime dob) {
        estimatedDateOfDelivery = convertToDate(edd);
        dateOfBirth = convertToDate(dob);
    }
}
