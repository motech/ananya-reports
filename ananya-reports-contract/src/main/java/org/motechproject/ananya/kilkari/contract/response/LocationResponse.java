package org.motechproject.ananya.kilkari.contract.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class LocationResponse {

    @JsonProperty
    private String block;

    @JsonProperty
    private String panchayat;

    @JsonProperty
    private String district;

    public LocationResponse(String district, String block, String panchayat) {
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
}
