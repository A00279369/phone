package com.cisco.phoneapp.restapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class UserCreationFailedException extends RuntimeException {
    public UserCreationFailedException(String s) {
        super(s);
    }
}
