package org.motechproject.ananya.kilkari.reports.domain.dimension;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubscriptionPack() {
        return subscriptionPack;
    }

    public void setSubscriptionPack(String subscriptionPack) {
        this.subscriptionPack = subscriptionPack;
    }
}
