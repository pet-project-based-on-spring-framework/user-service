package com.trl.userservice.core.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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
        expected.setCreatedDate(LocalDateTime.of(2021, 1, 1, 10, 30));
        expected.setUpdatedDate(LocalDateTime.of(2021, 2, 1, 20, 40));

        UserDto result = new UserDto.Builder()
                .withId(1L)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .withCreatedDate(LocalDateTime.of(2021, 1, 1, 10, 30))
                .withUpdatedDate(LocalDateTime.of(2021, 2, 1, 20, 40))
                .build();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

}
