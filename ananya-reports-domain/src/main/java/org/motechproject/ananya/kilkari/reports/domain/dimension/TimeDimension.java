package org.motechproject.ananya.kilkari.reports.domain.dimension;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "hour_dimension")
@NamedQuery(name = TimeDimension.FIND_BY_HOUR_AND_MINUTE, query = "select t from TimeDimension t where t.hourOfDay=:hour_of_day and t.minuteOfHour=:minute_of_hour ")
public class TimeDimension implements  Comparable<TimeDimension>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hour_of_day")
    private Integer hourOfDay;

    @Column(name = "minute_of_hour")
    private Integer minuteOfHour;

    public static final String FIND_BY_HOUR_AND_MINUTE = "find.by.hour.and.minute";

    public TimeDimension() {
    }

    public TimeDimension(DateTime dateTime) {
        hourOfDay = dateTime.getHourOfDay();
        minuteOfHour = dateTime.getMinuteOfHour();
    }

    public Integer getId() {
        return id;
    }

    public Integer getHourOfDay() {
        return hourOfDay;
    }

    public Integer getMinuteOfHour() {
        return minuteOfHour;
    }

    @Override
    public int compareTo(TimeDimension that) {
        DateTime thisDate = new DateTime(1, 1, 1, this.getHourOfDay(), this.getMinuteOfHour());
        DateTime thatDate = new DateTime(1, 1, 1, that.getHourOfDay(), that.getMinuteOfHour());
        return thisDate.compareTo(thatDate);
    }
}
