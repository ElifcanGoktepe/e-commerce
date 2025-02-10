package com.elifcan.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    PASSWORD_ERROR(4007, "Entered password is unvalid.", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXIST(4006,"Böyle bir ürün bulunmamaktadır.", HttpStatus.BAD_REQUEST),
    CART_NOT_EXIST(4006,"Önce sepet oluşturunuz.", HttpStatus.BAD_REQUEST),
    EMAIL_SIFRE_HATASI(4004, "Kullanıcı adi ya da şifre hatalıdır.",HttpStatus.BAD_REQUEST),
    SIFREHATASI(4003, "Girilen şifreler uyuşmamaktadır.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(4002, "Entered parameter is invalid.", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS(4005, "Bu kategori zaten kayıtlıdır.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER(5000,"Unexpected error occurs.",HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatus httpStatus; // type of request
}
