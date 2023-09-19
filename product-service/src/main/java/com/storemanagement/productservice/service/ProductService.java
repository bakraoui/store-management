package com.storemanagement.productservice.service;

import com.storemanagement.productservice.dtos.ProductRequest;
import com.storemanagement.productservice.dtos.ProductResponse;
import com.storemanagement.productservice.exceptions.ProductNotValidException;
import com.storemanagement.productservice.helpers.ProductRequestDtoHandler;
import com.storemanagement.productservice.helpers.ProductResult;
import com.storemanagement.productservice.mapper.ProductMapper;
import com.storemanagement.productservice.models.Product;
import com.storemanagement.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.storemanagement.productservice.helpers.ProductRequestDtoHandler.isProductDescriptionValid;
import static com.storemanagement.productservice.helpers.ProductRequestDtoHandler.isProductNameValid;
import static com.storemanagement.productservice.helpers.ProductResult.SUCCESS;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest request) {

        var result = isProductDescriptionValid()
                .and(isProductNameValid())
                .apply(request);

        if (!result.equals(SUCCESS)) {
            throw new ProductNotValidException(result.getMessage());
        }

        Product product = ProductMapper.mapProductRequestToProduct(request);

        // add some validation
        productRepository.save(product);
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::mapProductToProductResponse)
                .toList();
    }
}
