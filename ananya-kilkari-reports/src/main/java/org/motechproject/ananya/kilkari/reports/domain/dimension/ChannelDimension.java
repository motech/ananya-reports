package org.motechproject.ananya.kilkari.reports.domain.dimension;

import javax.persistence.*;

@Entity
@Table(name = "channel_dimension")
@NamedQuery(name = ChannelDimension.FIND_BY_CHANNEL_NAME, query = "select c from channel_dimension c where c.channel=:channel")
public class ChannelDimension {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "channel")
    private String channel;
    public static final String FIND_BY_CHANNEL_NAME = "find.by.channel.name";

    public ChannelDimension() {
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
