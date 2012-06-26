package org.motechproject.ananya.kilkari.reports.domain.dimension;

import javax.persistence.*;

@Entity
@Table(name = "operator_dimension")
@NamedQuery(name = OperatorDimension.FIND_BY_OPERATOR_NAME, query = "select o from OperatorDimension o where o.operator=:operator")
public class OperatorDimension {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operator")
    private String operator;
    public static final String FIND_BY_OPERATOR_NAME = "find.by.operator.name";

    public OperatorDimension() {
    }

    public OperatorDimension(String operator) {
        this.operator = operator;
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
}
