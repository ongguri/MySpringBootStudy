package com.example.testproject1.data.repository;

import com.example.testproject1.data.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    /* 정렬과 페이징 */

    // Asc : 오름차순, Desc : 내림차순
    List<ProductEntity> findByProductNameContainingOrderByProductStockAsc(String name);
    List<ProductEntity> findByProductNameContainingOrderByProductStockDesc(String name);

    // 여러 정렬 기준 사용
    List<ProductEntity> findByProductNameContainingOrderByProductPriceAscProductStockDesc(String name);

    // 매개변수를 활용한 정렬
    List<ProductEntity> findByProductNameContaining(String name, Sort sort);

    // 페이징 처리하기
    List<ProductEntity> findByProductPriceGreaterThan(Integer price, Pageable pageable);


    /* @Query 사용하기 */
    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > 2000")
    List<ProductEntity> findByProductPriceBasis();

    @Query(value = "SELECT * FROM product p WHERE p.product_price > 2000", nativeQuery = true)  // 테이블로 만들어진 컬럼 명 그대로 작성
    // Hibernate 로 출력된 쿼리문도 value 그대로 나온다.
    List<ProductEntity> findByProductPriceBasisNativeQuery();

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > ?1")  // price 를 ?1 부분에 주입받는 형식
    List<ProductEntity> findByProductPriceWithParameter(Integer price);

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > :price")  // price 를 :price 에 주입받는 형식
    List<ProductEntity> findByProductPriceWithParameterNaming(Integer price);

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > :pri")  // 이름을 맞춰줄 수 없을 때 @Param 어노테이션으로 이름을 맞출 수 있다.
    List<ProductEntity> findByProductPriceWithParameterNaming2(@Param("pri") Integer price);

    @Query(value = "SELECT * FROM product WHERE product_price > :price",
            countQuery = "SELECT count(*) FROM productEntity WHERE productPrice > ?1",
            nativeQuery = true)  // countQuery : paging 처리 할 때 몇 페이지인지, 페이지 마다의 사이즈를 설정하는데 그걸 참고할 쿼리
    List<ProductEntity> findByProductPriceWithParameterPaging(Integer price, Pageable pageable);

}
