package com.storemanagement.productservice.helpers;

import com.storemanagement.productservice.dtos.ProductRequest;

import java.util.function.Function;

public interface ProductRequestDtoHandler extends Function<ProductRequest, ProductResult> {

    static ProductRequestDtoHandler isProductNameValid() {
        return request -> request.getName().isBlank() ?
                ProductResult.PRODUCT_NAME_NOT_VALID : ProductResult.SUCCESS;
    }

    static ProductRequestDtoHandler isProductDescriptionValid() {
        return request -> request.getDescription().isBlank() ?
                ProductResult.PRODUCT_DESCRIPTION_NOT_VALID : ProductResult.SUCCESS;
    }

    default ProductRequestDtoHandler and(ProductRequestDtoHandler other) {
        return request -> {
            ProductResult result = this.apply(request);
            return result.equals(ProductResult.SUCCESS) ? other.apply(request) : result;
        };
    }

}
