package com.essenstore.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {

    public static ResponseEntity<?> response(Object obj, HttpStatus httpStatus) {
        return new ResponseEntity<>(obj, httpStatus);
    }
}
