package com.trl.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

public class RunDevelopmentProfileWithTestcontainers {

    private static final Logger LOG = LoggerFactory.getLogger(RunDevelopmentProfileWithTestcontainers.class);

    public static void main(String[] args) {
        var application = UserServiceApplication.createSpringApplication();
        application.addInitializers(new Initializer());
        application.run(args);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Container
        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2-alpine")
                .withDatabaseName("user-service-db")
                .withUsername("developer")
                .withPassword("0123456789");

        public static Map<String, String> getProperties() {
            Startables.deepStart(Stream.of(postgres)).join();

            String databaseUrl = postgres.getJdbcUrl();
            String databaseName = postgres.getDatabaseName();
            String username = postgres.getUsername();
            String password = postgres.getPassword();

            LOG.info(">>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>> Database url: [{}]", databaseUrl);
            LOG.info(">>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>> Database name: [{}]", databaseName);
            LOG.info(">>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>> Database username: [{}]", username);
            LOG.info(">>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>>   >>> Database password: [{}]", password);

            return Map.of(
                    "spring.datasource.url", databaseUrl,
                    "spring.datasource.username", username,
                    "spring.datasource.password", password
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            var env = context.getEnvironment();
            env.getPropertySources().addFirst(new MapPropertySource(
                    "testcontainers",
                    (Map) getProperties()
            ));
        }
    }

}
