package org.motechproject.ananya.kilkari.reports.web.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;

public class LocationResponse {

    @JsonProperty
    private String block;

    @JsonProperty
    private String panchayat;

    @JsonProperty
    private String district;

    public LocationResponse(LocationDimension locationDimension) {
        district = locationDimension.getDistrict();
        block = locationDimension.getBlock();
        panchayat = locationDimension.getPanchayat();
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
