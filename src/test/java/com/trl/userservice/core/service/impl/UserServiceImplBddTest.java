package com.trl.userservice.core.service.impl;

import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.repository.UserRepository;
import com.trl.userservice.core.service.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserServiceImplBdd")
class UserServiceImplBddTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldThrowUserNotFoundException() {
        final long userId = 123456789;

        given(repository.findById(eq(userId))).willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.get(userId));

        then(repository).should().findById(eq(userId));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldReturnUser() {
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

        service.get(userId);

        then(repository).should().findById(eq(userId));
        then(repository).shouldHaveNoMoreInteractions();
    }

}
