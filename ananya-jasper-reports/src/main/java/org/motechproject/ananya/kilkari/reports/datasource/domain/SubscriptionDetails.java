package org.motechproject.ananya.kilkari.reports.datasource.domain;

import java.sql.Date;

public class SubscriptionDetails {
    private String subscriptionId;
    private Long msisdn;
    private String pack;
    private String channel;
    private String operator;
    private String status;
    private Date requestedDate;
    private Date activationDate;
    private Date completionDate;
    private String name;
    private Integer age;
    private LocationDetails location;
    private Date dob;
    private Date edd;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public String getPack() {
        return pack;
    }

    public String getChannel() {
        return channel;
    }

    public String getOperator() {
        return operator;
    }

    public String getStatus() {
        return status;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public LocationDetails getLocation() {
        return location;
    }

    public Date getDob() {
        return dob;
    }

    public Date getEdd() {
        return edd;
    }
}
