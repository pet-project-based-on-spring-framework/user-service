package com.trl.userservice.init;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TestDataInitializer implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TestDataInitializer.class);

    private final String dataSourceUrl;

    private final String dataSourceUsername;

    private final String dataSourcePassword;

    private final String testDataScriptLocation;

    private final boolean enabled;

    public TestDataInitializer(
            @Value("${spring.datasource.url}") String dataSourceUrl,
            @Value("${spring.datasource.username}") String dataSourceUsername,
            @Value("${spring.datasource.password}") String dataSourcePassword,
            @Value("${test.data.script-location}") String testDataScriptLocation,
            @Value("${test.data.enabled}") boolean enabled) {
        this.dataSourceUrl = dataSourceUrl;
        this.dataSourceUsername = dataSourceUsername;
        this.dataSourcePassword = dataSourcePassword;
        this.testDataScriptLocation = testDataScriptLocation;
        this.enabled = enabled;
    }

    @Override
    public void run(String... args) {
        if (enabled) {
            Configuration configuration = buildFlywayConfiguration();

            Flyway flyway = new Flyway(configuration);
            flyway.migrate();

            LOG.debug("In run - Database has been successfully populated with test data");
        }
    }

    private Configuration buildFlywayConfiguration() {
        ClassicConfiguration config = new ClassicConfiguration();

        config.setDataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword);
        config.setLocations(new Location(testDataScriptLocation));
        config.setValidateOnMigrate(false);

        return config;
    }
}

