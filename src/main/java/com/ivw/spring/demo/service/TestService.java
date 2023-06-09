package com.ivw.spring.demo.service;

import com.ivw.spring.demo.common.QueryConstants;
import com.ivw.spring.demo.config.PostgresConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TestService {

    @Autowired
    private PostgresConfig postgresConfig;

    private final Map<String, Connection> CONNECTION_MAP = new ConcurrentHashMap<>();


    public void getAll() throws SQLException {
        Connection connection = CONNECTION_MAP.computeIfAbsent("123", key -> {
            try {
                return postgresConfig.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        PreparedStatement getAllFromConfig = connection.prepareStatement(QueryConstants.GET_ALL_FROM_CONFIG);
        ResultSet resultSet = getAllFromConfig.executeQuery();
        while (resultSet.next()) {
            System.out.println("name : " + resultSet.getString("name"));
        }
        connection.close();
    }
}
