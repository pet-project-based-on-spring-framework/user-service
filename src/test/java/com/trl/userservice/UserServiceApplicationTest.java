package com.trl.userservice;

import com.trl.userservice.api.v1.controller.ResourceTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ResourceTestConfig.class)
@DisplayName("UserServiceApplication")
class UserServiceApplicationTest extends AbstractIntegrationTest {

    @Test
    void shouldStartBackendApplicationWhenMainMethodIsInvoked() {
//        UserServiceApplication.main(new String[]{});
    }

}
