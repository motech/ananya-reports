package org.motechproject.ananya.reports.kilkari.domain.dimension;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "time_dimension")
@NamedQuery(name = DateDimension.FIND_BY_DAY_MONTH_YEAR, query = "select t from DateDimension t where t.year=:year and t.month=:month and t.day=:day")
public class DateDimension {

    public static final String FIND_BY_DAY_MONTH_YEAR = "find.by.day.month.year";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "day")
    private Integer day;

    @Column(name = "week")
    private Integer week;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "date")
    private Date date;

    public DateDimension() {
    }

    public DateDimension(DateTime time) {
        this(time.getDayOfYear(), time.getWeekOfWeekyear(), time.getMonthOfYear(), time.getYear(), time.toDate());
    }

    public DateDimension(Integer day, Integer week, Integer month, Integer year, Date date) {
        this.day = day;
        this.week = week;
        this.month = month;
        this.year = year;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean matches(DateTime time) {
        return this.day.equals(time.getDayOfYear())
                && this.week.equals(time.getWeekOfWeekyear())
                && this.month.equals(time.getMonthOfYear())
                && this.year.equals(time.getYear());
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
