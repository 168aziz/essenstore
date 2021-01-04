package com.essenstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends BasicApiException {

    public NotFoundException() {
        super(NOT_FOUND.value(), "Not Found");
    }

    public NotFoundException(String message) {
        super(NOT_FOUND.value(), message);
    }
}
