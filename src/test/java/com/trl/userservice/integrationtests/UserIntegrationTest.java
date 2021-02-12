package com.trl.userservice.integrationtests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static com.trl.userservice.api.v1.controller.UserController.BASE_URL;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/sqlscripts/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sqlscripts/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void shouldReturnUserDto() throws Exception {
        final long userId = 1L;

        final String expectedJsonResult = "{\"id\":1,\"firstName\":\"Roman\",\"lastName\":\"Tsyupryk\",\"username\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"birthday\":\"1988-06-26\",\"createdDate\":\"2021-01-01T08:30:00\",\"updatedDate\":\"2021-01-01T11:30:00\"}";

        mockMvc.perform(get(BASE_URL + "/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(containsString(expectedJsonResult)))
                .andDo(print());
    }

    @Sql(value = {"/sqlscripts/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sqlscripts/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void shouldReturnPageOfUserDto_defaultSizeOfPageIsTen() throws Exception {

        final String expectedJsonResult = "{\"id\":4,\"firstName\":\"first_name_test_3\",\"lastName\":\"last_name_test_3\",\"username\":\"username_test_3\",\"email\":\"test_email_3@mail.com\",\"password\":\"strong_password\",\"birthday\":\"2000-01-03\",\"createdDate\":\"2021-01-04T08:30:00\",\"updatedDate\":\"2021-01-04T11:30:00\"},{\"id\":3,\"firstName\":\"first_name_test_2\",\"lastName\":\"last_name_test_2\",\"username\":\"username_test_2\",\"email\":\"test_email_2@mail.com\",\"password\":\"strong_password\",\"birthday\":\"2000-01-02\",\"createdDate\":\"2021-01-03T08:30:00\",\"updatedDate\":\"2021-01-03T11:30:00\"},{\"id\":2,\"firstName\":\"first_name_test_1\",\"lastName\":\"last_name_test_1\",\"username\":\"username_test_1\",\"email\":\"test_email_1@mail.com\",\"password\":\"strong_password\",\"birthday\":\"2000-01-01\",\"createdDate\":\"2021-01-02T08:30:00\",\"updatedDate\":\"2021-01-02T11:30:00\"},{\"id\":1,\"firstName\":\"Roman\",\"lastName\":\"Tsyupryk\",\"username\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"birthday\":\"1988-06-26\",\"createdDate\":\"2021-01-01T08:30:00\",\"updatedDate\":\"2021-01-01T11:30:00\"}";

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(containsString(expectedJsonResult)))
                .andDo(print());
    }

}
