package com.example.testproject1.data.repository;

import com.example.testproject1.data.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest  // transactional 이 이뤄져서 작업이 끝난 후에는 롤백이 진행된다.
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach  // 테스트 전에 실행되면서 값을 저장
    void GenerateData() {
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 3000));
        productRepository.save(getProduct(Integer.toString(--count), count = count + 2, 1500, 200));
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1500));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1000));
        productRepository.save(getProduct(Integer.toString(count), count++, 500, 10000));
        productRepository.save(getProduct(Integer.toString(count), count++, 8500, 3500));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count, 5100, 1700));
    }

    private ProductEntity getProduct(String id, int nameNumber, int price, int stock) {
        return new ProductEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");  // 테스트 데이터가 뭔지 먼저 보여줌

        // 상품4 인 값을 조회
        List<ProductEntity> foundEntities = productRepository.findByProductName("상품4");

        for(ProductEntity productEntity : foundEntities) {
            System.out.println(productEntity.toString());
        } // 조회된 값을 출력

        // queryBy 로 동일 기능 수행
        List<ProductEntity> queryEntities = productRepository.queryByProductName("상품4");

        for(ProductEntity productEntity : queryEntities) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    void existTest() {  // 값이 있는지 체크
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        System.out.println(productRepository.existsByProductName("상품4"));
        System.out.println(productRepository.existsByProductName("상품2"));  // 테스트 데이터에 없는 상품
    }

    @Test
    void countTest() {  // 쿼리 결과의 개수 반환
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        System.out.println(productRepository.countByProductName("상품4"));
    }

    @Test
    @Transactional  // spring data jpa 에서 제공해주는 repository 를 사용하면 기본적으로 트랜잭션이 유지가 되지 않는다. 그래서 해당 어노테이션을 붙어야 한다.
    void deleteTest() {
        System.out.println("before : " + productRepository.count());

        productRepository.deleteByProductId("1");
        productRepository.removeByProductId("9");

        System.out.println("After : " +productRepository.count());
    }

    @Test
    void topTest() {  // 특별히 정한 게 없다면 id 로 오름차순 정렬된 후 조회
        productRepository.save(getProduct("109", 123,1500, 5000));
        productRepository.save(getProduct("101", 123, 2500, 5000));
        productRepository.save(getProduct("102", 123, 3500, 5000));
        productRepository.save(getProduct("103", 123, 4500, 5000));
        productRepository.save(getProduct("104", 123, 1000, 5000));
        productRepository.save(getProduct("105", 123, 2000, 5000));
        productRepository.save(getProduct("106", 123, 3900, 5000));
        productRepository.save(getProduct("107", 123, 4000, 5000));

        List<ProductEntity> foundEntities = productRepository.findFirst5ByProductName("상품123");
        for(ProductEntity productEntity : foundEntities) {
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> foundEntities2 = productRepository.findTop3ByProductName("상품123");
        for(ProductEntity productEntity : foundEntities2) {
            System.out.println(productEntity.toString());
        }
    }


    // 조건자 키워드 테스트
    @Test
    void isEqualsTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        System.out.println(productRepository.findByProductIdIs("1"));
        System.out.println(productRepository.findByProductIdEquals("1"));
    }

    @Test
    void notTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundEntities = productRepository.findByProductIdNot("1");
        for(ProductEntity productEntity : foundEntities){
            System.out.println(productEntity);
        }
//        System.out.println(productRepository.findByProductIdNot("1"));
        System.out.println(productRepository.findByProductIdIsNot("1"));
    }

    @Test
    void nullTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        System.out.println(productRepository.findByProductStockIsNull());
        System.out.println(productRepository.findByProductStockIsNotNull());
    }

    @Test
    void andTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        System.out.println(productRepository.findTopByProductIdAndProductName("1", "상품1"));
    }

    @Test
    void greaterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> productEntities = productRepository.findByProductPriceGreaterThan(5000);

        for(ProductEntity productEntity : productEntities) {
            System.out.println(productEntity);
        }
    }

    @Test
    void containTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        System.out.println(productRepository.findByProductNameContaining("상품1"));  // 상품10 도 포함됨. 상품1 문자열이 포함되어 있기 때문.
    }


    // 정렬과 페이징
    @Test
    void orderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        // ProductStock 오름차순
        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductStockAsc("상품");
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        // ProductStock 내림차순
        foundProducts = productRepository.findByProductNameContainingOrderByProductStockDesc("상품");
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    void multiOrderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        // productPrice 오름차순 후 productStock 내림차순
        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductPriceAscProductStockDesc("상품");
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    void orderByWithParameterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContaining(
                "상품", Sort.by(Sort.Order.asc("productPrice")));  // property 명은 엔티티명과 동일하게.
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductNameContaining("상품",
                Sort.by(Sort.Order.asc("productPrice"), Sort.Order.asc("productStock")));
        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    void pagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceGreaterThan(200,
                PageRequest.of(0, 2));  // 첫번째 페이지를 가져오고 사이즈는 2로 하겠다는 의미

        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductPriceGreaterThan(200,
                PageRequest.of(2, 2));  // 두번째 페이지를 가져오고 사이즈는 2로 하겠다는 의미

        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductPriceGreaterThan(200,
                PageRequest.of(4, 2));  // 네번째 페이지를 가져오고 사이즈는 2로 하겠다는 의미

        for(ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    // @Query 사용하기
    @Test
    public void queryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceBasis();  // 설정한 쿼리(price 이상)의 값을 조회
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void nativeQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceBasisNativeQuery();
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void parameterQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceWithParameter(2000);
        // 파라미터로 값을 받고 있기 때문에 쿼리문에 ?1 에 값이 들어가고 hibernate 결과에 ? 로 표시된다.
        // 시큐어 코드 중 하나. 민감정보를 숨길 수 있는 방식 중 하나이다.
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void parameterNamingQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceWithParameterNaming(2000);
        // 1? 로 쿼리를 작성했을때와 같은 결과가 나온다.
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void parameterNamingQueryTest2() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceWithParameterNaming2(2000);
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void nativeQueryPagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("=== Test Data Start ===");
        for(ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("=== Test Data end ===");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceWithParameterPaging(2000,
                PageRequest.of(2, 2));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }
}
