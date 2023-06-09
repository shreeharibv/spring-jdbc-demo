package com.ivw.spring.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Component
public class PostgresConfig {

    @Autowired
    private DataSource dataSource;


    public Connection getConnection() throws SQLException {
        JdbcTemplate jdbcTemplate1 = new JdbcTemplate(dataSource);
        return Objects.requireNonNull(jdbcTemplate1.getDataSource()).getConnection();
    }
}
