package org.motechproject.ananya.kilkari.reports.repository;

import org.apache.commons.lang.StringUtils;
import org.motechproject.ananya.kilkari.reports.domain.dimension.OperatorDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllOperatorDimensions {
    @Autowired
    private DataAccessTemplate template;

    public AllOperatorDimensions() {
    }

    public OperatorDimension fetchFor(String operator) {
        return (OperatorDimension) template.getUniqueResult(OperatorDimension.FIND_BY_OPERATOR_NAME,
                new String[]{"operator"}, new Object[]{StringUtils.upperCase(operator)});
    }
}
