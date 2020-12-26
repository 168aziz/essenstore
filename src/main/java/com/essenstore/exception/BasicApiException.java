package com.essenstore.exception;

import lombok.Getter;

@Getter
public class BasicApiException extends RuntimeException{

    private final int code;

    private final String message;

    public BasicApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
