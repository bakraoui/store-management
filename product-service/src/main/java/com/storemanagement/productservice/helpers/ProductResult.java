package com.storemanagement.productservice.helpers;

public enum ProductResult {
    SUCCESS("Valid Input"),
    PRODUCT_NAME_NOT_VALID("The name is not valid"),
    PRODUCT_DESCRIPTION_NOT_VALID("The description is not valid"),;

    private String message;

    ProductResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
