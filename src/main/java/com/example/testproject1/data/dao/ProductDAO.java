package com.example.testproject1.data.dao;

import com.example.testproject1.data.entity.ProductEntity;

// dao : 인터페이스로 생성
// impl 패키지의 ProductDAOImpl.class 가 사용할 예정
public interface ProductDAO {
    ProductEntity saveProduct(ProductEntity productEntity);
    ProductEntity getProduct(String productId);
    // 상품
}
