package com.trl.userservice.api.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.trl.userservice.api.v1.controller.UserController.BASE_URL;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import({ResourceTestConfig.class})
@DisplayName("UserController")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService service;

    @MockBean
    private UserToUserDtoConverter converter;

    @Test
    void userId_shouldBeGreaterThanOrEqualsToOne() throws Exception {
        long userId = 0L;

        String messageError = "userId must be greater than or equal to 1";

        mockMvc.perform(get(BASE_URL + "/{userId}", userId))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(containsString(messageError)))
                .andDo(print());
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

        final String expectedJsonResult = mapper.writeValueAsString(converterResult);

        when(service.getById(eq(userId))).thenReturn(serviceResult);
        when(converter.convert(eq(serviceResult))).thenReturn(converterResult);

        mockMvc.perform(get(BASE_URL + "/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(containsString(expectedJsonResult)))
                .andDo(print());
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void shouldReturnPageOfUserDto_defaultSizeOfPageIsTen(List<User> testDataUserList, List<UserDto> testDataUserDtoList) throws Exception {

        int defaultPageSize = 10;
        List<UserDto> userDtoList = testDataUserDtoList.stream().limit(defaultPageSize).collect(Collectors.toList());
        List<User> userList = testDataUserList.stream().limit(defaultPageSize).collect(Collectors.toList());

        Page<User> servicePageResponse = new PageImpl<>(userList);

        Page<UserDto> expectedPageResponse = new PageImpl<>(userDtoList);
        String expectedJsonResult = mapper.writeValueAsString(expectedPageResponse);

        when(service.getAll(any(Pageable.class))).thenReturn(servicePageResponse);
        when(converter.convert(eq(userList))).thenReturn(userDtoList);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(containsString(expectedJsonResult)))
                .andDo(print());
    }

}
