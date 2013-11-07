package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberChangeSubscriptionReportRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.SubscriberReportRequest;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

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

    @Column(name = "start_week_number")
    private Integer startWeekNumber;

    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;

    public Subscriber() {
    }

    public Subscriber(String name, Integer ageOfBeneficiary, DateTime estimatedDateOfDelivery,
                      DateTime dateOfBirth, ChannelDimension channelDimension, LocationDimension locationDimension,
                      DateDimension dateDimension, OperatorDimension operatorDimension, Integer startWeekNumber, DateTime lastModifiedTime) {
        this.name = name;
        this.ageOfBeneficiary = ageOfBeneficiary;
        this.startWeekNumber = startWeekNumber;
        this.estimatedDateOfDelivery = convertToDate(estimatedDateOfDelivery);
        this.dateOfBirth = convertToDate(dateOfBirth);
        this.channelDimension = channelDimension;
        this.locationDimension = locationDimension;
        this.dateDimension = dateDimension;
        this.operatorDimension = operatorDimension;
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStartWeekNumber() {
        return startWeekNumber;
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

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void updateWith(SubscriberReportRequest subscriberReportRequest, LocationDimension locationDimension, DateDimension dateDimension) {
        ageOfBeneficiary = subscriberReportRequest.getBeneficiaryAge();
        name = subscriberReportRequest.getBeneficiaryName();
        this.dateDimension = dateDimension;
        this.locationDimension = locationDimension;
        lastModifiedTime = new Timestamp(subscriberReportRequest.getCreatedAt().getMillis());
    }

    public void updateWith(SubscriberChangeSubscriptionReportRequest subscriberReportRequest) {
        dateOfBirth = convertToDate(subscriberReportRequest.getDateOfBirth());
        estimatedDateOfDelivery = convertToDate(subscriberReportRequest.getEstimatedDateOfDelivery());
        startWeekNumber =  subscriberReportRequest.getStartWeekNumber();
        lastModifiedTime = new Timestamp(subscriberReportRequest.getCreatedAt().getMillis());
    }
    
    public void updateSubscriptionDates(DateTime edd, DateTime dob, Integer startWeekNumber, DateTime lastModifiedTime) {
        if (isNotUpdatable(edd, dob, startWeekNumber))
            return;
        this.startWeekNumber = startWeekNumber;
        estimatedDateOfDelivery = convertToDate(edd);
        dateOfBirth = convertToDate(dob);
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getMillis());
    }

    private boolean isNotUpdatable(DateTime edd, DateTime dob, Integer startWeekNumber) {
        return isEqualDate(convertToDate(edd), this.estimatedDateOfDelivery)
                && isEqualDate(convertToDate(dob), this.dateOfBirth)
                && (startWeekNumber == null ? this.startWeekNumber == null : startWeekNumber.equals(this.startWeekNumber));
    }

    private boolean isEqualDate(Date date1, Date date2) {
        return date1 == null ? date2 == null : date1.equals(date2);
    }

    private Date convertToDate(DateTime dateTime) {
        return dateTime != null ? new Date(dateTime.toDate().getTime()) : null;
    }

    private DateTime convertToDateTime(Date date) {
        return date != null ? new DateTime(date) : null;
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
