package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "operator_dimension")
@NamedQuery(name = OperatorDimension.FIND_BY_OPERATOR_NAME, query = "select o from OperatorDimension o where UPPER(o.operator)=:operator")
public class OperatorDimension {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operator")
    private String operator;

    @Column(name = "start_pulse")
    private Integer startPulse;

    @Column(name = "end_pulse")
    private Integer endPulse;


    public static final String FIND_BY_OPERATOR_NAME = "find.by.operator.name";

    public OperatorDimension() {
    }

    public OperatorDimension(String operator, Integer startPulse, Integer endPulse) {
        this.operator = operator;
        this.startPulse = startPulse;
        this.endPulse = endPulse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getStartPulse() {
        return startPulse;
    }

    public Integer getEndPulse() {
        return endPulse;
    }

    public void setStartPulse(Integer startPulse) {
        this.startPulse = startPulse;
    }

    public void setEndPulse(Integer endPulse) {
        this.endPulse = endPulse;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
