package org.motechproject.ananya.reports.kilkari.repository;

import org.apache.commons.lang.StringUtils;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
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
        return (LocationDimension) template.getUniqueResult(LocationDimension.FIND_BY_DISTRICT_BLOCK_AND_PANCHAYAT,new String[] {"district","block","panchayat"},new String[] {StringUtils.upperCase(district),StringUtils.upperCase(block),StringUtils.upperCase(panchayat)});
    }

    public void createOrUpdate(LocationDimension locationDimension) {
        template.saveOrUpdate(locationDimension);
    }
}
