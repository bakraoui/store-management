package com.storemanagement.productservice.mapper;

import com.storemanagement.productservice.dtos.ProductRequest;
import com.storemanagement.productservice.dtos.ProductResponse;
import com.storemanagement.productservice.models.Product;

public class ProductMapper {

    public static Product mapProductRequestToProduct(ProductRequest request) {
        return Product.builder()
                .price(request.getPrice())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }
}
