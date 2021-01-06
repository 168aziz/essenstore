package com.essenstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(BAD_REQUEST)
public class BadRequestException extends BasicApiException {

    public BadRequestException() {
        super(BAD_REQUEST.value(), "Bad Request Exception");
    }

    public BadRequestException(String message) {
        super(NOT_FOUND.value(), message);
    }
}

