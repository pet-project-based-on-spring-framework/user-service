package com.trl.userservice.core.dto;

import com.trl.userservice.core.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserDtoBuilder")
class UserDtoBuilderTest {

    @Test
    void testBuilder() {
        UserDto expected = new UserDto();
        expected.setId(1L);
        expected.setFirstName("Roman");
        expected.setLastName("Tsyupryk");
        expected.setUsername("TRL");
        expected.setEmail("tsyupryk.roman@gmail.com");
        expected.setPassword("strong_password");
        expected.setBirthday(LocalDate.of(1988, 6, 26));

        UserDto result = new UserDto.Builder()
                .withId(1L)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .build();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

}
