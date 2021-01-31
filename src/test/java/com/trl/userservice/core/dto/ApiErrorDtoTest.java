package com.trl.userservice.core.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ApiErrorDtoBuilder")
class ApiErrorDtoBuilderTest {

    @Test
    void testBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        String errorMessage = "some error message";
        String path = "some path";

        ApiErrorDto expected = new ApiErrorDto();
        expected.setTimestamp(timestamp);
        expected.setErrorMessage(errorMessage);
        expected.setPath(path);

        ApiErrorDto result = new ApiErrorDto.Builder()
                .withTimestamp(timestamp)
                .withErrorMessage(errorMessage)
                .withPath(path)
                .build();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

}

