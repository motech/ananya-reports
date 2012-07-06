package org.motechproject.ananya.kilkari.reports.service;

import org.motechproject.ananya.kilkari.reports.domain.dimension.LocationDimension;
import org.motechproject.ananya.kilkari.reports.repository.AllLocationDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    @Autowired
    private AllLocationDimensions allLocationDimensions;

    public LocationService() {
    }

    public LocationService(AllLocationDimensions allLocationDimensions) {
        this.allLocationDimensions = allLocationDimensions;
    }

    @Transactional
    public LocationDimension fetchFor(String district, String block, String panchayat) {
        return allLocationDimensions.fetchFor(district, block, panchayat);
    }
}
