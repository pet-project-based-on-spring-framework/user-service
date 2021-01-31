package com.trl.userservice.argumentprovider;

import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    private final List<User> testDataUserList = new ArrayList<>();

    private final List<UserDto> testDataUserDtoList = new ArrayList<>();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        initData();

        return Stream.of(
                Arguments.of(testDataUserList, testDataUserDtoList)
        );
    }

    private void initData() {
        testDataUserList.add(
                new User.Builder()
                        .withId(1L)
                        .withFirstName("testUserFirstName1")
                        .withLastName("testUserLastName1")
                        .withUsername("testUserUsername1")
                        .withEmail("testUserEmail1@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 1))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(2L)
                        .withFirstName("testUserFirstName2")
                        .withLastName("testUserLastName2")
                        .withUsername("testUserUsername2")
                        .withEmail("testUserEmail2@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 2))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(3L)
                        .withFirstName("testUserFirstName3")
                        .withLastName("testUserLastName3")
                        .withUsername("testUserUsername3")
                        .withEmail("testUserEmail3@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 3))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(4L)
                        .withFirstName("testUserFirstName4")
                        .withLastName("testUserLastName4")
                        .withUsername("testUserUsername4")
                        .withEmail("testUserEmail4@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 4))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(5L)
                        .withFirstName("testUserFirstName5")
                        .withLastName("testUserLastName5")
                        .withUsername("testUserUsername5")
                        .withEmail("testUserEmail5@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 5))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(6L)
                        .withFirstName("testUserFirstName6")
                        .withLastName("testUserLastName6")
                        .withUsername("testUserUsername6")
                        .withEmail("testUserEmail6@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 6))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(7L)
                        .withFirstName("testUserFirstName7")
                        .withLastName("testUserLastName7")
                        .withUsername("testUserUsername7")
                        .withEmail("testUserEmail7@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 7))
                        .build()
        );

        testDataUserList.add(
                new User.Builder()
                        .withId(8L)
                        .withFirstName("testUserFirstName8")
                        .withLastName("testUserLastName8")
                        .withUsername("testUserUsername8")
                        .withEmail("testUserEmail8@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 8))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(1L)
                        .withFirstName("testUserFirstName1")
                        .withLastName("testUserLastName1")
                        .withUsername("testUserUsername1")
                        .withEmail("testUserEmail1@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 1))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(2L)
                        .withFirstName("testUserFirstName2")
                        .withLastName("testUserLastName2")
                        .withUsername("testUserUsername2")
                        .withEmail("testUserEmail2@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 2))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(3L)
                        .withFirstName("testUserFirstName3")
                        .withLastName("testUserLastName3")
                        .withUsername("testUserUsername3")
                        .withEmail("testUserEmail3@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 3))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(4L)
                        .withFirstName("testUserFirstName4")
                        .withLastName("testUserLastName4")
                        .withUsername("testUserUsername4")
                        .withEmail("testUserEmail4@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 4))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(5L)
                        .withFirstName("testUserFirstName5")
                        .withLastName("testUserLastName5")
                        .withUsername("testUserUsername5")
                        .withEmail("testUserEmail5@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 5))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(6L)
                        .withFirstName("testUserFirstName6")
                        .withLastName("testUserLastName6")
                        .withUsername("testUserUsername6")
                        .withEmail("testUserEmail6@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 6))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(7L)
                        .withFirstName("testUserFirstName7")
                        .withLastName("testUserLastName7")
                        .withUsername("testUserUsername7")
                        .withEmail("testUserEmail7@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 7))
                        .build()
        );

        testDataUserDtoList.add(
                new UserDto.Builder()
                        .withId(8L)
                        .withFirstName("testUserFirstName8")
                        .withLastName("testUserLastName8")
                        .withUsername("testUserUsername8")
                        .withEmail("testUserEmail8@email.com")
                        .withPassword("strong_password")
                        .withBirthday(LocalDate.of(2000, 1, 8))
                        .build()
        );

    }

}

