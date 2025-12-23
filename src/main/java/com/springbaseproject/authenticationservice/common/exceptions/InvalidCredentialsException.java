package com.springbaseproject.authenticationservice.common.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException() {
        super("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
}