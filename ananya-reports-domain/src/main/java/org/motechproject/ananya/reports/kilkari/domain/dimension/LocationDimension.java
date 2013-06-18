package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "location_dimension")
@NamedQuery(name = LocationDimension.FIND_BY_DISTRICT_BLOCK_AND_PANCHAYAT, query = "select l from LocationDimension l where UPPER(l.district)=:district and UPPER(l.block)=:block and UPPER(l.panchayat)=:panchayat")
public class LocationDimension {

    public static final String FIND_BY_DISTRICT_BLOCK_AND_PANCHAYAT = "find.by.district.block.and.panchayat";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "district")
    private String district;

    @Column(name = "block")
    private String block;

    @Column(name = "panchayat")
    private String panchayat;

    @Column(name = "status")
    private String status;

    @Column(name = "last_modified_time")
    private Timestamp lastModified;

    @ManyToOne
    @JoinColumn(name = "alternate_location")
    private LocationDimension alternateLocation;


    public LocationDimension() {
    }

    public LocationDimension(String district, String block, String panchayat, String status) {
        this.district = WordUtils.capitalizeFully(district);
        this.block = WordUtils.capitalizeFully(block);
        this.panchayat = WordUtils.capitalizeFully(panchayat);
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public String getBlock() {
        return block;
    }

    public String getPanchayat() {
        return panchayat;
    }

    public String getStatus() {
        return status;
    }

    public LocationStatus getStatusAsEnum() {
        return LocationStatus.getFor(status);
    }

    public boolean isInvalidLocation() {
        return getStatusAsEnum() != null ? getStatusAsEnum().equals(LocationStatus.INVALID) : false;
    }

    public LocationDimension getAlternateLocation() {
        return alternateLocation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public void setAlternateLocation(LocationDimension alternateLocation) {
        this.alternateLocation = alternateLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationDimension)) return false;

        LocationDimension that = (LocationDimension) o;

        return new EqualsBuilder()
                .append(this.district, that.district)
                .append(this.block, that.block)
                .append(this.panchayat, that.panchayat)
                .append(this.status, that.status)
                .append(this.alternateLocation, that.alternateLocation)
                .isEquals();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.district)
                .append(this.block)
                .append(this.panchayat)
                .append(this.status)
                .append(this.alternateLocation)
                .hashCode();
    }
}
