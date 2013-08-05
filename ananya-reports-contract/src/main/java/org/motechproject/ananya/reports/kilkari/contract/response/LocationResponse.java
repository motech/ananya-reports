package org.motechproject.ananya.reports.kilkari.contract.response;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class LocationResponse {

    @JsonProperty
    private String state;

    @JsonProperty
    private String block;

    @JsonProperty
    private String panchayat;

    @JsonProperty
    private String district;

    public LocationResponse() {
    }

    public LocationResponse(String state, String district, String block, String panchayat) {
        this.state = state;
        this.district = district;
        this.block = block;
        this.panchayat = panchayat;
    }

    public String getBlock() {
        return block;
    }

    public String getPanchayat() {
        return panchayat;
    }

    public String getDistrict() {
        return district;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationResponse)) return false;

        LocationResponse that = (LocationResponse) o;

        return new EqualsBuilder()
                .append(this.state, that.state)
                .append(this.district, that.district)
                .append(this.block, that.block)
                .append(this.panchayat, that.panchayat)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.state)
                .append(this.district)
                .append(this.block)
                .append(this.panchayat)
                .hashCode();
    }
}
