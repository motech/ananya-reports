package org.motechproject.ananya.reports.kilkari.service;

import org.motechproject.ananya.reports.kilkari.domain.dimension.OperatorDimension;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {

    public int fetchDurationInPulse(OperatorDimension operatorDimension, int durationInSeconds) {
        int durationInMilliSeconds = durationInSeconds * 1000;
        int durationForPulseCalculation = durationInMilliSeconds - operatorDimension.getStartPulse() < 0 ? 0 : durationInMilliSeconds;
        double pulseDuration = operatorDimension.getEndPulse() - operatorDimension.getStartPulse();
        return (int) Math.ceil(durationForPulseCalculation/ pulseDuration);
    }
}
