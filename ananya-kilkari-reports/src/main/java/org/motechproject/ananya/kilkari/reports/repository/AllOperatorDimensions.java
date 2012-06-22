package org.motechproject.ananya.kilkari.reports.repository;

import org.motechproject.ananya.kilkari.reports.domain.dimension.ChannelDimension;
import org.motechproject.ananya.kilkari.reports.domain.dimension.OperatorDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpDivide;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AllOperatorDimensions {
    @Autowired
    private DataAccessTemplate template;

    public AllOperatorDimensions() {
    }

    public OperatorDimension fetchFor(String operator) {
        return (OperatorDimension) template.getUniqueResult(OperatorDimension.FIND_BY_OPERATOR_NAME,
                new String[]{"channel"}, new Object[]{operator});
    }
}
