package com.essenstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class BadRequestException extends BasicApiException {

    public BadRequestException() {
        super(BAD_REQUEST.value(), "Bad Request Exception");
    }

}

