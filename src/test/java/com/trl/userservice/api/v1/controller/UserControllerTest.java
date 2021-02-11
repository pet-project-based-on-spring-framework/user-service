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

        when(service.get(eq(userId))).thenReturn(serviceResult);
        when(converter.convert(eq(serviceResult))).thenReturn(converterResult);

        mockMvc.perform(get(BASE_URL + "/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(containsString(expectedJsonResult)))
                .andDo(print());
    }

}
