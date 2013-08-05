package org.motechproject.ananya.reports.kilkari.repository;

import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.apache.commons.lang.StringUtils.upperCase;

@Repository
public class AllLocationDimensions {

    private DataAccessTemplate template;

    @Autowired
    public AllLocationDimensions(DataAccessTemplate template) {
        this.template = template;
    }

    public LocationDimension fetchFor(String state, String district, String block, String panchayat) {
        return (LocationDimension) template.getUniqueResult(LocationDimension.FIND_BY_STATE_DISTRICT_BLOCK_AND_PANCHAYAT,
                new String[] {"state","district","block","panchayat"},
                new String[] {upperCase(state), upperCase(district), upperCase(block), upperCase(panchayat)});
    }

    public void createOrUpdate(LocationDimension locationDimension) {
        template.saveOrUpdate(locationDimension);
    }
}
