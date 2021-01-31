package com.trl.userservice.api.v1.exceptionhandler;

import com.trl.userservice.core.dto.ApiErrorDto;
import com.trl.userservice.core.service.DateTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.trl.userservice.util.WebUtil.getFullRequestUri;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerResource extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlerResource.class);

    private final DateTimeService dateTimeService;

    public GlobalExceptionHandlerResource(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
        LOG.warn("In handleConstraintViolationException - {}", e.getMessage());

        ApiErrorDto error = createApiError(buildMessageFromConstraintViolations(e.getConstraintViolations()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private String buildMessageFromConstraintViolations(Collection<ConstraintViolation<?>> violations) {
        return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
    }

    private ApiErrorDto createApiError(String errorMessage) {
        return new ApiErrorDto.Builder()
                .withTimestamp(dateTimeService.now())
                .withErrorMessage(errorMessage)
                .withPath(getFullRequestUri())
                .build();
    }

}
