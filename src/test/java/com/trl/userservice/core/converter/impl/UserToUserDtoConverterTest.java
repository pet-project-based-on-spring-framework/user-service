package com.trl.userservice.core.converter.impl;

import com.trl.userservice.config.ModelMapperConfig;
import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig({
        ModelMapperConfig.class,
        UserToUserDtoConverter.class
})
@DisplayName("UserToUserDtoConverter")
class UserToUserDtoConverterTest {

    @Autowired
    private UserToUserDtoConverter converter;

    @Test
    void shouldGetSourceClass() {
        Class<User> expected = User.class;

        Class<User> result = converter.getSourceClass();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldGetTargetClass() {
        Class<UserDto> expected = UserDto.class;

        Class<UserDto> result = converter.getTargetClass();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldConvertUserToUserDto() {
        User source = new User.Builder()
                .withId(1L)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .build();

        UserDto expected = new UserDto.Builder()
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

        UserDto result = converter.convert(source);

        assertThat(result).usingRecursiveComparison().ignoringFields("createdDate", "updatedDate").isEqualTo(expected);
    }

}
