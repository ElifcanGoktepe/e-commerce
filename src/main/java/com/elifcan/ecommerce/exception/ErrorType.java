package com.elifcan.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    PASSWORD_ERROR(4007, "Entered password is incorrect.", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXIST(4006,"Product not exist.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(4006,"User not exist.", HttpStatus.BAD_REQUEST),
    CART_NOT_EXIST(4006,"Cart not exist.", HttpStatus.BAD_REQUEST),
    EMAIL_PASSWORD_ERROR(4004, "Username or password is incorrect.",HttpStatus.BAD_REQUEST),
    SIFREHATASI(4003, "Password is incorrect.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(4002, "Entered parameter is invalid.", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS(4005, "Category already exist.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER(5000,"Unexpected error occurs.",HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatus httpStatus; // type of request
}
