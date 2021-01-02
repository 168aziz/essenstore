package com.essenstore.exception;

import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    ResponseEntity<?> handleValidationExceptions(BindException ex) {
        var errors = new ArrayList<RepositoryConstraintViolationExceptionMessage.ValidationError>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    var msg = RepositoryConstraintViolationExceptionMessage
                            .ValidationError.of(ex.getObjectName(), ((FieldError) error).getField(), ((FieldError) error).getRejectedValue(), error.getDefaultMessage());
                    errors.add(msg);
                });
        return ResponseEntity.badRequest().body(errors);
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

    @ExceptionHandler({BadRequestException.class, EntityNotFoundException.class})
    ResponseEntity<?> badRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
