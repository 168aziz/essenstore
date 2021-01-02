package com.essenstore.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyVerifiedException extends BasicApiException {

    public UserAlreadyVerifiedException() {
        super(HttpStatus.BAD_REQUEST.value(), "User Already Verified");
    }

}
