package com.trl.userservice.core.service.impl;

import com.trl.userservice.argumentprovider.UserArgumentsProvider;
import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.repository.UserRepository;
import com.trl.userservice.core.service.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserServiceImpl")
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldThrowUserNotFoundException() {
        final long userId = 123456789;

        when(repository.findById(eq(userId))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.getById(userId));
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

        when(repository.findById(eq(userId))).thenReturn(Optional.of(expected));

        User result = service.getById(userId);

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void shouldReturnPageOfUsers(List<User> testDataUserList,
                             List<UserDto> testDataUserDtoList) {
        List<User> expectedUserList = testDataUserList;
        int pageSize = expectedUserList.size();
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Page<User> pageResponse = new PageImpl<>(expectedUserList);

        when(repository.findAll(eq(pageRequest))).thenReturn(pageResponse);

        Page<User> result = service.getAll(pageRequest);

        assertThat(result.getContent()).isEqualTo(expectedUserList);
    }

}
