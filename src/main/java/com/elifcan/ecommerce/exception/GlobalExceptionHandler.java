package com.elifcan.ecommerce.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // spring creates object to run
@Slf4j // used to log
public class GlobalExceptionHandler {

    // to catch possible errors, all objects should be checked
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(RuntimeException exception){
        return createErrorMessage(exception, ErrorType.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(ECommerceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> LibraryManagingException(ECommerceException exception){
        return createErrorMessage(exception, exception.getErrorType(), exception.getErrorType().getHttpStatus(), null);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) // catch validation error
    @ResponseBody
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> fields = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(field -> {
            fields.add(field.getField() + " : " + field.getDefaultMessage());
        });
        return createErrorMessage(exception, ErrorType.BAD_REQUEST, HttpStatus.BAD_REQUEST, fields);
    }

    /**
     * All possible errors should have a format. All errors have to be logged.
     */

    public ResponseEntity<ErrorMessage> createErrorMessage(Exception exception, ErrorType errorType, HttpStatus httpStatus, List<String> fields){
        log.error("Exception caught in GlobalExceptionHandler", exception);
        log.error("Error message : {}", errorType.getMessage() + fields);
        return new ResponseEntity<>(ErrorMessage.builder()
                .code(errorType.getCode())
                .fields(fields)
                .isSuccess(false)
                .message(errorType.getMessage())
                .build(), httpStatus);
    }

}
