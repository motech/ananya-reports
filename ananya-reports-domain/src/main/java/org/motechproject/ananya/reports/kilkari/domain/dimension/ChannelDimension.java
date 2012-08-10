package org.motechproject.ananya.reports.kilkari.domain.dimension;

import javax.persistence.*;

@Entity
@Table(name = "channel_dimension")
@NamedQuery(name = ChannelDimension.FIND_BY_CHANNEL, query = "select c from ChannelDimension c where UPPER(c.channel)=:channel")
public class ChannelDimension {

    public static final String FIND_BY_CHANNEL= "find.by.channel";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "channel")
    private String channel;

    public ChannelDimension() {
    }

    public ChannelDimension(String channel) {
        this.channel = channel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
