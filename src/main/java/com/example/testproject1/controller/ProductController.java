package com.example.testproject1.controller;

import com.example.testproject1.data.dto.ProductDto;
import com.example.testproject1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {

        long startTime = System.currentTimeMillis();

        // info 레벨로 로그 찍기.
        LOGGER.info("[ProductController] perform {} of Around Hub API.", "getProduct");

        ProductDto productDto = productService.getProduct(productId);  // 서비스 처리

        // 서비스가 처리 된 이후 로그
        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}," +
                "Response Time = {}ms", productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(),
                productDto.getProductStock(), (System.currentTimeMillis() - startTime));
        return productDto;
    }

    // http://localhost:8080/api/v1/product-api/product
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        // @Valid 어노테이션으로 검증(validation) 활성화


        // @Valid 어노테이션으로 검증을 못하는 경우를 위해 아래와 같은 예시로 하는 경우가 있다.
        // Validation Code Example
        /*
        if (productDto.getProductId().equals("") || productDto.getProductId().isEmpty()) {
            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }
         */

        // 동일한 값을 받아서 동일 값을 밷는다. 제대로 DB에 저장이 되었다 싶으면 그 값을 그대로 밷는 역할.
        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info("[createProduct] Response >> productId = {}, productName = {}, productPrice = {}, productStock = {}"
                , response.getProductId(), response.getProductName(), response.getProductPrice(),
                response.getProductStock());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }
}
