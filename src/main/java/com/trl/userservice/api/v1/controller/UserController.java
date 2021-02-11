package com.trl.userservice.api.v1.controller;

import com.trl.userservice.config.ApiVersion;
import com.trl.userservice.core.converter.TypeConverter;
import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.trl.userservice.util.WebUtil.getFullRequestUri;

import javax.validation.constraints.Min;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping(
        path = UserController.BASE_URL
)
@Validated
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/users";

    private final UserService service;

    private final TypeConverter<User, UserDto> converter;

    public UserController(UserService service, TypeConverter<User, UserDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @Operation(
            summary = "Return user.",
            description = "Return user with provided user identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Everything fine."),
                    @ApiResponse(responseCode = "400", description = "User identifier is incorrect."),
                    @ApiResponse(responseCode = "404", description = "User by this identifier not found."),
                    @ApiResponse(description = "User", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class)))},
            parameters = {@Parameter(description = "Id of the user to be retrieved. Cannot be null or zero.", required = true)}
    )
    @GetMapping(path = "/{userId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(
            @PathVariable(name = "userId")
            @Min(value = 1, message = "userId must be greater than or equal to 1") Long userId) {
        LOG.debug("Received GET request to retrieve user, request URI:[{}]", getFullRequestUri());

        User serviceResult = service.getById(userId);

        return ResponseEntity.ok(converter.convert(serviceResult));
    }

    @Operation(
            summary = "Page with Users will be returned.",
            description = "Page with Users will be returned.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Everything fine."),
                    @ApiResponse(responseCode = "404", description = "User by this identifier not found."),
                    @ApiResponse(description = "Page with users.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pageable.class)))}
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserDto>> getAllUsers(@PageableDefault(sort = "createdDate", direction = DESC) Pageable pageable) {
        LOG.debug("Received GET request to retrieve user, request URI:[{}]", getFullRequestUri());

        Page<User> serviceResult = service.getAll(pageable);

        List<UserDto> contentResult = converter.convert(serviceResult.getContent());

        return ResponseEntity.ok(new PageImpl<>(contentResult, serviceResult.getPageable(), serviceResult.getTotalElements()));
    }

}

