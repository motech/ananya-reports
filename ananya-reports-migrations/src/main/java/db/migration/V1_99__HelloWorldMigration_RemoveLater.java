package db.migration;

import com.googlecode.flyway.core.migration.java.JavaMigration;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_99__HelloWorldMigration_RemoveLater implements JavaMigration {
    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        System.out.println("Hello World!!");
    }
}
