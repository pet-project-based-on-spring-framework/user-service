package com.trl.userservice.core.service.caching;

import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.repository.UserRepository;
import com.trl.userservice.core.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = CachingTestConfig.CachingTestingApplication.class, webEnvironment = WebEnvironment.NONE)
@Import(CachingTestConfig.class)
@DisplayName("UserServiceImplCachingBdd")
class UserServiceImplCachingBddTest {

    private static final String USER_CACHE = "userCache";

    private static final String KEY_BY_ID = "by_user_id_";

    @MockBean
    private UserRepository repository;

    @Autowired
    private CachingTestHelper cachingHelper;

    @Autowired
    private UserService service;

    @AfterEach
    void tearDown() {
        cachingHelper.clearCaches(USER_CACHE);
    }

    @Test
    void shouldReturnUser_fromStorageIfNoRecordInCache() {
        final long userId = 1L;

        User expected = new User.Builder()
                .withId(userId)
                .withFirstName("testUserFirstName1")
                .withLastName("testUserLastName1")
                .withUsername("testUserUsername1")
                .withEmail("testUserEmail1@email.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(2000, 1, 1))
                .build();

        given(repository.findById(eq(userId))).willReturn(Optional.of(expected));

        cachingHelper.assertNoRecordInCache(USER_CACHE, KEY_BY_ID + userId);

        User result = service.getById(userId);

        then(repository).should().findById(eq(userId));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldReturnUser_fromCache() {
        final long userId = 1L;

        User expected = new User.Builder()
                .withId(userId)
                .withFirstName("testUserFirstName1")
                .withLastName("testUserLastName1")
                .withUsername("testUserUsername1")
                .withEmail("testUserEmail1@email.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(2000, 1, 1))
                .build();

        cachingHelper.putInCache(USER_CACHE, KEY_BY_ID + userId, expected);

        User result = service.getById(userId);

        then(repository).shouldHaveNoMoreInteractions();
    }

}
