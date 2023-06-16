package com.example.testproject1.service;

import com.example.testproject1.data.dto.ProductDto;

public interface ProductService {

    // 재사용성을 위한 인터페이스
    ProductDto saveProduct(String productId, String productName, int productPrice, int productStock);
    ProductDto getProduct(String productId);
}
