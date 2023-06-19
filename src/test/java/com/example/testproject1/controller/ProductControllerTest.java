package com.example.testproject1.controller;

import com.example.testproject1.data.dto.ProductDto;
import com.example.testproject1.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
//@AutoConfigureWebMvc // 이 어노테이션을 통해 MockMvc 를 Builder 없이 주입받을 수 있다.
public class ProductControllerTest {  // main 에 있는 controller 와 경로를 맞춰야 한다.

    @Autowired
    private MockMvc mockMvc;  // MockMvc 를 Builder 없이 주입받기 위해서 @AutoConfigureWebMvc 사용

    // ProductController 에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
    @MockBean
    ProductServiceImpl productService;

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @Test
    @DisplayName("Product 데이터 가져오기 테스트")  // 설명 어노테이션, 보기 편하게 해당 어노테이션 사용
    void getProductTest() throws Exception {

        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메서드
        given(productService.getProduct("12315")).willReturn(
                new ProductDto("15871", "pen", 5000, 2000));

        String productId = "12315";

        // andExpect : 기대하는 값이 나왔는지 체크해볼 수 있는 메서드
        mockMvc.perform(  // RestAPI 테스트를 할 수 있는 환경을 만들어줌
                get("/api/v1/product-api/product/" + productId))  // get : 실제로 어떤 통신을 할 것인지 정의해줌
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());  // 테스트 내용 프린트

        // verify : 해당 객체의 메서드가 실행되었는지 체크해줌
        verify(productService).getProduct("12315");
    }

    // http://localhost:8080/api/v1/product-api/product
    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        //Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return 을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메서드
        given(productService.saveProduct("15871", "pen", 5000, 2000)).willReturn(
                new ProductDto("15871", "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder().productId("15871").productName("pen")
                .productPrice(5000).productStock(2000).build();
        Gson gson = new Gson();  // 구글에서 만든 json 형태를 자유롭게 다룰 수 있게끔 편의를 제공한 라이브러리. 디펜던시 추가.
        String content = gson.toJson(productDto);

        // Gson 말고, 아래 코드로 json 형태 변경 작업을 대체할 수 있음
        String json = new ObjectMapper().writeValueAsString(productDto);

        mockMvc.perform(
                post("/api/v1/product-api/product/")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())  // 실행 결과값 예상
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());  // 테스트 내용 프린트
        verify(productService).saveProduct("15871", "pen", 5000, 2000);
    }
}
