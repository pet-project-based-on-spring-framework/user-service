package com.trl.userservice;

import com.trl.userservice.api.v1.controller.ResourceTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = ResourceTestConfig.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("UserServiceApplication")
class UserServiceApplicationTest {

    @Test
    void shouldStartBackendApplicationWhenMainMethodIsInvoked() {
        UserServiceApplication.main(new String[]{});
    }

}
