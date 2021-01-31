package com.trl.userservice.api.v1.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trl.userservice.core.converter.impl.UserToUserDtoConverter;
import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.trl.userservice.api.v1.resource.UserController.BASE_URL;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        given(service.get(eq(userId))).willReturn(serviceResult);
        given(converter.convert(eq(serviceResult))).willReturn(converterResult);

        mockMvc.perform(get(BASE_URL + "/{userId}", userId));

        then(service).should().get(eq(userId));
        then(service).shouldHaveNoMoreInteractions();
        then(converter).should().convert(eq(serviceResult));
        then(converter).shouldHaveNoMoreInteractions();
    }

}
