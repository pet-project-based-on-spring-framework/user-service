package com.trl.userservice.api.v1.controller;

import com.trl.userservice.argumentprovider.UserArgumentsProvider;
import com.trl.userservice.core.converter.impl.UserToUserDtoConverter;
import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import static com.trl.userservice.api.v1.controller.UserController.BASE_URL;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = UserController.class)
@Import({ResourceTestConfig.class})
@DisplayName("UserControllerBdd")
class UserControllerBddTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserToUserDtoConverter converter;

    @Test
    void userId_shouldBeGreaterThanOrEqualsToOne() throws Exception {
        long userId = 0L;

        mockMvc.perform(get(BASE_URL + "/{userId}", userId));

        then(service).shouldHaveNoMoreInteractions();
        then(converter).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldReturnUserDto() throws Exception {
        long userId = 1L;

        User serviceResult = new User.Builder()
                .withId(userId)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .build();

        UserDto converterResult = new UserDto.Builder()
                .withId(userId)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .build();

        given(service.getById(eq(userId))).willReturn(serviceResult);
        given(converter.convert(eq(serviceResult))).willReturn(converterResult);

        mockMvc.perform(get(BASE_URL + "/{userId}", userId));

        then(service).should().getById(eq(userId));
        then(service).shouldHaveNoMoreInteractions();
        then(converter).should().convert(eq(serviceResult));
        then(converter).shouldHaveNoMoreInteractions();
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void shouldReturnPageOfUserDto_defaultSizeOfPageIsTen(List<User> testDataUserList, List<UserDto> testDataUserDtoList) throws Exception {

        int defaultPageSize = 10;
        List<UserDto> userDtoList = testDataUserDtoList.stream().limit(defaultPageSize).collect(Collectors.toList());
        List<User> userList = testDataUserList.stream().limit(defaultPageSize).collect(Collectors.toList());

        Page<User> servicePageResponse = new PageImpl<>(userList);

        given(service.getAll(any(Pageable.class))).willReturn(servicePageResponse);
        given(converter.convert(eq(userList))).willReturn(userDtoList);

        mockMvc.perform(get(BASE_URL));

        then(service).should().getAll(any(Pageable.class));
        then(service).shouldHaveNoMoreInteractions();
        then(converter).should().convert(eq(userList));
        then(converter).shouldHaveNoMoreInteractions();
    }

}
