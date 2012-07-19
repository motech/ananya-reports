package org.motechproject.ananya.kilkari.reports.domain.dimension;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "subscription_pack_dimension")
@NamedQuery(name = SubscriptionPackDimension.FIND_BY_PACK_NAME, query = "select s from SubscriptionPackDimension s where UPPER(s.subscriptionPack)=:subscriptionPack")
public class SubscriptionPackDimension {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subscription_pack")
    private String subscriptionPack;
    public static final String FIND_BY_PACK_NAME = "find.by.pack.name";

    public SubscriptionPackDimension() {
    }

    public SubscriptionPackDimension(String subscriptionPack) {
        this.subscriptionPack = subscriptionPack;
    }

    public Integer getId() {
        return id;
    }

    public String getSubscriptionPack() {
        return subscriptionPack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionPackDimension)) return false;

        SubscriptionPackDimension that = (SubscriptionPackDimension) o;

        return new EqualsBuilder()
                .append(this.id, that.id)
                .append(this.subscriptionPack, that.subscriptionPack)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.subscriptionPack)
                .hashCode();
    }
}
