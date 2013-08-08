package db.migration;

import com.googlecode.flyway.core.migration.java.JavaMigration;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_10__TimeDimensionSeedMigration_For_Hour_Dimension implements JavaMigration {
    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        for (int hourOfDay = 0; hourOfDay < 24; ++hourOfDay) {
            for (int minuteOfHour = 0; minuteOfHour < 60; ++minuteOfHour) {
                jdbcTemplate.update("insert into report.hour_dimension (hour_of_day, minute_of_hour) values (?, ?)", new Object[]{hourOfDay, minuteOfHour});
            }
        }
    }
}
