package com.trl.userservice;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Container
        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2-alpine")
                .withReuse(true);

        public static Map<String, String> getProperties() {
            Startables.deepStart(Stream.of(postgres)).join();

            return Map.of(
                    "spring.datasource.url", postgres.getJdbcUrl(),
                    "spring.datasource.username", postgres.getUsername(),
                    "spring.datasource.password", postgres.getPassword(),
                    "test.data.enabled", "false",
                    "server.port", "8180"
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
