package com.example.demo;


import com.example.demo.config.DatabaseProperties;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

@Component
public class DatabaseInfoContributor implements InfoContributor {
    private final DataSource ds;
    private final DatabaseProperties props;
    private final JdbcTemplate jdbc;

    public DatabaseInfoContributor(DataSource ds, DatabaseProperties props, JdbcTemplate jdbc) {
        this.ds = ds;
        this.props = props;
        this.jdbc = jdbc;
    }

    @Override
    public void contribute(Info.Builder builder) {
        try (var conn = ds.getConnection()) {
            DatabaseMetaData md = conn.getMetaData();
            var url = md.getURL();
            var product = md.getDatabaseProductName();
            var version = md.getDatabaseProductVersion();

            Integer count = null;
            try {
                count = jdbc.queryForObject("SELECT COUNT(*) FROM customers", Integer.class);
            } catch (Exception ignored) {}

            builder.withDetail("database",
                    java.util.Map.of(
                            "vendor", props.getVendor(),
                            "url", url,
                            "product", product,
                            "version", version,
                            "user", props.getUser(),
                            "activeProfile", String.join(",", org.springframework.core.env.AbstractEnvironment
                                    .ACTIVE_PROFILES_PROPERTY_NAME.split("")), // keep minimal; profile is obvious from env too
                            "customersCount", count
                    )
            );
        } catch (Exception e) {
            builder.withDetail("database", java.util.Map.of("error", e.getMessage()));
        }
    }
}
