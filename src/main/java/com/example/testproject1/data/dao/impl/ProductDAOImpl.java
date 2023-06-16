package com.example.testproject1.data.dao.impl;

import com.example.testproject1.data.dao.ProductDAO;
import com.example.testproject1.data.entity.ProductEntity;
import com.example.testproject1.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements ProductDAO {

    ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    } // 의존 주입

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);  // jpa repository 는 기본적으로 save, getId 등을 기본 제공
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity = productRepository.getById(productId);
        return productEntity;
    }
}
