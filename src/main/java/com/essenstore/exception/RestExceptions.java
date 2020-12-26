package com.essenstore.exception;

import com.essenstore.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.essenstore.utils.Utils.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, LinkedList<String>>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMsg = error.getDefaultMessage();
                    var list = errors.getOrDefault(fieldName, new LinkedList<>());
                    list.add(errorMsg);
                    errors.put(fieldName, list);
                });

        return response(errors, BAD_REQUEST);
    }
}
