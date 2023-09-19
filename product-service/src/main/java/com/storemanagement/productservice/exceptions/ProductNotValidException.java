package com.storemanagement.productservice.exceptions;

public class ProductNotValidException extends RuntimeException{

    public ProductNotValidException(String message) {
        super(message);
    }

}
