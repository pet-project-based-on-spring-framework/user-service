package com.trl.userservice.api.v1.exceptionhandler;

import com.trl.userservice.core.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserExceptionHandlerResource extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(UserExceptionHandlerResource.class);

    public UserExceptionHandlerResource() {
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException e) {
        LOG.debug("In handleUserNotFoundException - {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}

