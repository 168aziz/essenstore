package com.essenstore.exception;

import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static com.essenstore.utils.Utils.response;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class RestExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
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

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<?> invalidAuthenticationException() {
        return ResponseEntity.status(FORBIDDEN).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        var errors = new ArrayList<RepositoryConstraintViolationExceptionMessage.ValidationError>();
        ex.getConstraintViolations()
                .forEach(violation -> {
                    var msg = RepositoryConstraintViolationExceptionMessage
                            .ValidationError.of(violation.getLeafBean().getClass().getSimpleName(), violation.getPropertyPath().toString(), violation.getInvalidValue(), violation.getMessage());
                    errors.add(msg);
                });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<?> notFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<?> badRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
