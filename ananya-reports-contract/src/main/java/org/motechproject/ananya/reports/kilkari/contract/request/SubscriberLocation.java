package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class SubscriberLocation implements Serializable {
    @JsonProperty
    private String district;
    @JsonProperty
    private String block;
    @JsonProperty
    private String panchayat;

    public SubscriberLocation() {}
    
    public SubscriberLocation(String district, String block, String panchayat) {
        this.district = district;
        this.block = block;
        this.panchayat = panchayat;
    }

    @JsonIgnore
    public String getDistrict() {
        return district;
    }

    @JsonIgnore
    public String getBlock() {
        return block;
    }

    @JsonIgnore
    public String getPanchayat() {
        return panchayat;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setPanchayat(String panchayat) {
        this.panchayat = panchayat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberLocation)) return false;

        SubscriberLocation that = (SubscriberLocation) o;

        return new EqualsBuilder()
                .append(this.district, that.district)
                .append(this.block, that.block)
                .append(this.panchayat, that.panchayat)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.district)
                .append(this.block)
                .append(this.panchayat)
                .hashCode();
    }
}
