package com.example.testproject1.data.repository;

import com.example.testproject1.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    // <ProductEntity, String> : ProductEntity 는 Repository 가 사용할 엔티티, 이 엔티티에서 사용되는 PK(여기서는 id값)의 데이터 값

}
