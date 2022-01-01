package com.cisco.phoneapp.restapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(String message) {
        super(message);
    }
}