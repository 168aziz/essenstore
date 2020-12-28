package com.essenstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class ActivationCodeExpiredException extends BasicApiException {
    public ActivationCodeExpiredException() {
        super(HttpStatus.REQUEST_TIMEOUT.value(), "Activation Code Expired");
    }
}
