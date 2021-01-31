package com.trl.userservice.core.service.exception;

import com.trl.userservice.exception.UserServiceException;

public class UserNotFoundException extends UserServiceException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
