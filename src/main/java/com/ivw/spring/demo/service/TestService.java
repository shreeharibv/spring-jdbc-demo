package com.ivw.spring.demo.service;

import com.ivw.spring.demo.common.QueryConstants;
import com.ivw.spring.demo.config.PostgresConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TestService {

    @Autowired
    PostgresConfig postgresConfig;

    private final Map<String, Connection> CONNECTION_MAP = new ConcurrentHashMap<>();
    public void getAll() throws SQLException {
        Connection connection = CONNECTION_MAP.computeIfAbsent("123", key -> postgresConfig.getConnection());
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryConstants.GET_ALL_FROM_CONFIG);
        while ( resultSet.next() ) {
            System.out.println("Schedule Name : " + resultSet.getString("name"));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
