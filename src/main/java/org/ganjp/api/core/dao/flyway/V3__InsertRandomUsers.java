package org.ganjp.api.core.dao.flyway;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 * Example of a Java-based migration using Spring {@link JdbcTemplate}.
 */
public class V3__InsertRandomUsers extends BaseJavaMigration {

    public void migrate(Context context) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                new SingleConnectionDataSource(context.getConnection(), true));
        for (int i = 1; i <= 8; i++) {
            String sql = String.format("insert into auth_user (id, name, password, created_by, created_at, is_active) " +
                    "values('000000000%d', 'random_user_%d', '123456', 'flyway_java','2020-06-21 21:40:53', 0)",
                    i, i);
            jdbcTemplate.execute(sql);
        }
    }
}
