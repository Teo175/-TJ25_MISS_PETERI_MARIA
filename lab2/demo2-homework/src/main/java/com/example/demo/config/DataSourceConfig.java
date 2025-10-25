package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // DEV: H2 in-memory
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        HikariConfig cfg = new HikariConfig();
        cfg.setDriverClassName("org.h2.Driver");
        cfg.setJdbcUrl("jdbc:h2:mem:devdb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1");
        cfg.setUsername("sa");
        cfg.setPassword("");
        return new HikariDataSource(cfg);
    }

    // PROD: primary
    @Bean
    @Profile("prod")
    @ConditionalOnExpression("!${app.db.useReplica:false}")
    public DataSource prodPrimaryDataSource(DatabaseProperties props) {
        HikariConfig cfg = new HikariConfig();
        cfg.setDriverClassName("org.postgresql.Driver");
        cfg.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", props.getHost(), props.getPort(), props.getName()));
        cfg.setUsername(props.getUser());
        cfg.setPassword(props.getPassword());
        return new HikariDataSource(cfg);
    }

    // PROD: replica
    @Bean
    @Profile("prod")
    @ConditionalOnExpression("${app.db.useReplica:false}")
    public DataSource prodReplicaDataSource(DatabaseProperties props) {
        HikariConfig cfg = new HikariConfig();
        cfg.setDriverClassName("org.postgresql.Driver");
        cfg.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", props.getHost(), props.getPort(), props.getName()));
        cfg.setUsername(props.getUser());
        cfg.setPassword(props.getPassword());
        cfg.setPoolName("replica-ds");
        return new HikariDataSource(cfg);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
