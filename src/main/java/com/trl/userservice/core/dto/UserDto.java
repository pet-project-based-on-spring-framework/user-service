package com.trl.userservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * This class is designed to represent Dto of user.
 *
 * @author Tsyupryk Roman
 */
@Schema(name = "User", description = "Dto that represent a user.")
public class UserDto {

    @Schema(example = "100", minimum = "0", exclusiveMinimum = true, description = "ID of user.", nullable = true, implementation = Long.class)
    @Positive
    private Long id;

    @Schema(required = true, example = "Tom", description = "First name of user.", implementation = String.class)
    @NotBlank
    private String firstName;

    @Schema(required = true, example = "King", description = "Last name of user.", implementation = String.class)
    @NotBlank
    private String lastName;

    @Schema(required = true, example = "tom", description = "Username of user.", implementation = String.class)
    @NotBlank
    private String username;

    @Schema(required = true, example = "tom@email.com", description = "Email of user.", implementation = String.class)
    @NotBlank
    private String email;

    @Schema(required = true, example = "secret_password", description = "Password of account.", implementation = String.class)
    @NotBlank
    private String password;

    @Schema(example = "2000-01-01", description = "Birthday of user.", implementation = LocalDate.class)
    private LocalDate birthday;

    @Schema(example = "2000-01-01", description = "Creation date of the user. Creation date will be generated automatically.", implementation = LocalDateTime.class)
    private LocalDateTime createdDate;

    @Schema(example = "2000-01-01", description = "Update date of the user. Update date will be generated automatically.", implementation = LocalDateTime.class)
    private LocalDateTime updatedDate;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("username='" + username + "'")
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("birthday=" + birthday)
                .add("createdDate=" + createdDate)
                .add("updatedDate=" + updatedDate)
                .toString();
    }

    public static final class Builder {

        private Long id;

        private String firstName;

        private String lastName;

        private String username;

        private String email;

        private String password;

        private LocalDate birthday;

        private LocalDateTime createdDate;

        private LocalDateTime updatedDate;

        public Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withUpdatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public UserDto build() {
            UserDto result = new UserDto();

            result.setId(this.id);
            result.setFirstName(this.firstName);
            result.setLastName(this.lastName);
            result.setUsername(this.username);
            result.setEmail(this.email);
            result.setPassword(this.password);
            result.setBirthday(this.birthday);
            result.setCreatedDate(this.createdDate);
            result.setUpdatedDate(this.updatedDate);

            return result;
        }

    }

}

