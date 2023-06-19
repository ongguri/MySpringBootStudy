package com.example.testproject1.service.impl;

import com.example.testproject1.data.dto.ProductDto;
import com.example.testproject1.data.entity.ProductEntity;
import com.example.testproject1.handler.impl.ProductDataHandlerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {ProductDataHandlerImpl.class, ProductServiceImpl.class})
// 위 방법으로 써도 되고, @ExtendWith 가 @SpringBootTest 에 포함되어 있어서 아래와 같이 필요한것만 갖다 쓸 수 있다.
//@ExtendWith(SpringExtension.class)
//@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

    @MockBean
    ProductDataHandlerImpl productDataHandler;

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void getProductTest() {
        // given
        Mockito.when(productDataHandler.getProductEntity("123"))  // getProductEntity 를 호출할 때,
                .thenReturn(new ProductEntity("123", "pen", 2000, 3000));  // 리턴

        ProductDto productDto = productService.getProduct("123");

        Assertions.assertEquals(productDto.getProductId(), "123");  // 두 개의 매개변수가 같은지 체크
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).getProductEntity("123");  // given 으로 세팅한 가정사항이 제대로 실행이 되었는지 확인
    }

    @Test
    public void saveProductTest() {
        // given
        Mockito.when(productDataHandler.saveProductEntity("123", "pen", 2000, 3000))
                .thenReturn(new ProductEntity("123", "pen", 2000, 3000));

        ProductDto productDto = productService.saveProduct("123", "pen", 2000, 3000);

        Assertions.assertEquals(productDto.getProductId(), "123");  // 두 개의 매개변수가 같은지 체크
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).saveProductEntity("123", "pen", 2000, 3000);
    }
}
