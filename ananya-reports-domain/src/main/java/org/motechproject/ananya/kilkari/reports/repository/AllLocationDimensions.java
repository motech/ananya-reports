package org.motechproject.ananya.kilkari.reports.repository;

import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class AllLocationDimensions {

    private DataAccessTemplate template;

    @Autowired
    public AllLocationDimensions(DataAccessTemplate template) {
        this.template = template;
    }

    public LocationDimension fetchFor(String district, String block, String panchayat) {
        return (LocationDimension) template.getUniqueResult(LocationDimension.FIND_BY_DISTRICT_BLOCK_AND_PANCHAYAT,new String[] {"district","block","panchayat"},new String[] {district,block,panchayat});
    }

}
