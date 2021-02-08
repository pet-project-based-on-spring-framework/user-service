package com.trl.userservice.core.service.caching;

import com.trl.userservice.core.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

@Import(com.trl.userservice.core.service.caching.CachingTestHelper.class)
@MockBean(PasswordEncoder.class)
@MockBean(UserRepository.class)
@TestConfiguration
public class CachingTestConfig {

    @SpringBootApplication(scanBasePackages = "com.trl.userservice.core.service")
    @EnableCaching
    public static class CachingTestingApplication {

        public static void main(String[] args) {
            SpringApplication.run(CachingTestingApplication.class, args);
        }

    }

}
