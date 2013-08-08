package db.migration;

import com.googlecode.flyway.core.migration.java.JavaMigration;
import org.joda.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_5__TimeDimensionSeedMigration implements JavaMigration {

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        LocalDate startDate = new LocalDate(2014, 1, 2);
        LocalDate endDate = new LocalDate(2016, 1, 1);
        while (isOnOrBefore(startDate, endDate)) {
            jdbcTemplate.update("insert into report.time_dimension (day, week, month, year, date) values (?, ?, ?, ?, ?)", new Object[] {startDate.getDayOfYear(), startDate.getWeekOfWeekyear(), startDate.getMonthOfYear(), startDate.getYear(), startDate.toDate()});
            startDate = startDate.plusDays(1);
        }
    }

    private boolean isOnOrBefore(LocalDate startDate, LocalDate endDate) {
        return startDate.toDateTimeAtStartOfDay().equals(endDate.toDateTimeAtStartOfDay()) || startDate.toDateTimeAtStartOfDay().isBefore(endDate.toDateTimeAtStartOfDay());
    }
}
