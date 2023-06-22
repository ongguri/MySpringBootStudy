package com.example.testproject1.data.repository;

import com.example.testproject1.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    // <ProductEntity, String> : ProductEntity 는 Repository 가 사용할 엔티티, 이 엔티티에서 사용되는 PK(여기서는 id값)의 데이터 값

    /* 쿼리 메서드의 주제 키워드 */

    // 조회
    List<ProductEntity> findByProductName(String name);  // findByProductEntityProductName / findBy(엔티티이름)ProductName 라고 써도 됨
    List<ProductEntity> queryByProductName(String name);

    // 존재 유무
    boolean existsByProductName(String name);

    // 쿼리 결과 개수
    long countByProductName(String name);

    // 삭제
    void deleteByProductId(String id);
    long removeByProductId(String id);

    // 값 개수 제한
    List<ProductEntity> findFirst5ByProductName(String name);
    List<ProductEntity> findTop3ByProductName(String name);


    /* 쿼리 메서드의 조건자 키워드 */

    // Is, Equals (생략 가능)
    // Logical Keyword : Is, Keyword Expressions : Is, Equals, (or no keyword)
    // findByNumber 메서드와 동일하게 동작
    ProductEntity findByProductIdIs(String id);
    ProductEntity findByProductIdEquals(String id);  // == findByProductId(String id)

    // (Is)Not
    List<ProductEntity> findByProductIdNot(String id);
    List<ProductEntity> findByProductIdIsNot(String id);

    // (Is)Null, (Is)NotNull
    List<ProductEntity> findByProductStockIsNull();
    List<ProductEntity> findByProductStockIsNotNull();  // findByProductStockNotNull 처럼 is 빼도 됨

    // And, Or -> 앞에 Is 가 생략된 상태
    List<ProductEntity> findTopByProductIdAndProductName(String id, String name);  // 단건 조회이기 때문에 Top 개수를 설정 안함

    // (Is)GreaterThan, (Is)LessThan, (Is)Between
    List<ProductEntity> findByProductPriceGreaterThan(Integer price);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<ProductEntity> findByProductNameContaining(String name);

}
