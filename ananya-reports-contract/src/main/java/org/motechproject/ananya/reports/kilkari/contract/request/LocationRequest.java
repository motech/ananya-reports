package org.motechproject.ananya.reports.kilkari.contract.request;

import org.apache.commons.lang.builder.EqualsBuilder;

public class LocationRequest {
    private String state;
    private String district;
    private String block;
    private String panchayat;

    public LocationRequest() {
    }

    public LocationRequest(String state, String district, String block, String panchayat) {
        this.state = state;
        this.district = district;
        this.block = block;
        this.panchayat = panchayat;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getBlock() {
        return block;
    }

    public String getPanchayat() {
        return panchayat;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        return "LocationRequest{" +
                "state='" + state +
                "', district='" + district +
                "', block='" + block +
                "', panchayat='" + panchayat +
                "'}";
    }
}
