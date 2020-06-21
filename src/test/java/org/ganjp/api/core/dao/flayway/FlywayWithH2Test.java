package org.ganjp.api.core.dao.flayway;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJpaTest
class FlywayWithH2Test {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void databaseHasBeenInitialized() {
        jdbcTemplate.execute("insert into auth_user (id, name, password, created_by, created_at, is_active) " +
                "values('12823923823', 'flyway_tester', '123456', 'unit-tester','2020-06-21 17:38:53', 0)");
        final List<AuthUser> authUsers = jdbcTemplate
                .query("SELECT id, name, password FROM auth_user", (rs, rowNum) -> new AuthUser(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password")
                ));

        Assertions.assertThat(authUsers).isNotEmpty();
        Assertions.assertThat(authUsers.size()).isEqualTo(2);
    }

    private static class AuthUser {
        public String id;
        public String name;
        public String password;

        public AuthUser(final String id, final String name, final String password) {
            this.id = id;
            this.name = name;
            this.password = password;
        }
    }
}