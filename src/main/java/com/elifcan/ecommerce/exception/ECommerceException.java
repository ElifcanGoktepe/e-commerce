package com.elifcan.ecommerce.exception;

import lombok.Getter;

@Getter
public class ECommerceException extends RuntimeException { // because error only occurs when the application run

    private ErrorType errorType;
    public ECommerceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
