package com.example.testproject1.controller;

import com.example.testproject1.data.dto.ProductDto;
import com.example.testproject1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    // http://localhost:8080/api/v1/product-api/product
    @PostMapping(value = "/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        // 동일한 값을 받아서 동일 값을 밷는다. 제대로 DB에 저장이 되었다 싶으면 그 값을 그대로 밷는 역할.
        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        return productService.saveProduct(productId, productName, productPrice, productStock);
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }
}
