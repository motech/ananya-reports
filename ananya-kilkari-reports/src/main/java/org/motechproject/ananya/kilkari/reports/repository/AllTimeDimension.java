package org.motechproject.ananya.kilkari.reports.repository;

import org.joda.time.DateTime;
import org.motechproject.ananya.kilkari.reports.domain.dimension.TimeDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AllTimeDimension {
    @Autowired
    private DataAccessTemplate template;

    public TimeDimension makeFor(DateTime dateTime) {
        TimeDimension timeDimension = new TimeDimension(dateTime);
        template.save(timeDimension);
        return timeDimension;
    }
}
