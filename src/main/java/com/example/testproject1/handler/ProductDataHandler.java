package com.example.testproject1.handler;

import com.example.testproject1.data.entity.ProductEntity;

public interface ProductDataHandler {

    ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);
    ProductEntity getProductEntity(String productId);
}
