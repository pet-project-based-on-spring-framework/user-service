package com.trl.userservice.core.dto;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ApiErrorDto {

    private LocalDateTime timestamp;

    private String errorMessage;

    private String path;

    public ApiErrorDto() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiErrorDto.class.getSimpleName() + "[", "]")
                .add("timestamp=" + timestamp)
                .add("errorMessage='" + errorMessage + "'")
                .add("path='" + path + "'")
                .toString();
    }

    public static final class Builder {

        private LocalDateTime timestamp;

        private String errorMessage;

        private String path;

        public Builder() {
        }

        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public ApiErrorDto build() {
            ApiErrorDto result = new ApiErrorDto();
            result.setTimestamp(this.timestamp);
            result.setErrorMessage(this.errorMessage);
            result.setPath(this.path);

            return result;
        }

    }

}

