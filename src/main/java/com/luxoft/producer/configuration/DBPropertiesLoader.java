package com.luxoft.producer.configuration;

import com.luxoft.producer.db.model.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBPropertiesLoader implements EnvironmentPostProcessor {
    /**
     * Name of the custom property source added by this post processor class
     */
    private static final String PROPERTY_SOURCE_NAME = "databaseProperties";

    private static final String QUERY = "SELECT * FROM configuration WHERE environment = ?";

    /**
     * Adds Spring Environment custom logic. This custom logic fetch properties from database and setting the highest precedence
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Map<String, Object> propertySource = new HashMap<>();

        String activeEnvironment = environment.getProperty("spring.profiles.active");

        if (activeEnvironment == null || activeEnvironment.isBlank())
            throw new RuntimeException("Active profile not set");

        // Build manually datasource to ServiceConfig
        DataSource ds = DataSourceBuilder
                .create()
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"))
                .url(environment.getProperty("spring.datasource.url"))
                .driverClassName(environment.getProperty("spring.datasource.driver-class-name"))
                .build();

        try (Connection connection = ds.getConnection();
                PreparedStatement preparedStatement = createPreparedStatement(connection, activeEnvironment);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Configuration configuration = new Configuration(rs);
                propertySource.put(configuration.getPropertyName(), configuration.getPropertyValue());
            }

            // Create a custom property source with the highest precedence and add it to Spring Environment
            environment.getPropertySources().addFirst(new OriginTrackedMapPropertySource(PROPERTY_SOURCE_NAME, propertySource));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement createPreparedStatement(Connection con, String environment) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(QUERY)) {
            ps.setString(1, environment);
            return ps;
        }
    }

}
