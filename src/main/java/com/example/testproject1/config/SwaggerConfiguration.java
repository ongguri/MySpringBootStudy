package com.example.testproject1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfiguration {

    private static final String API_NAME = "Programmers Spring Boot Application";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "프로그래머스 스프링 부트 애플리케이션입니다.";

    // 접속 경로 : http://localhost:8080/swagger-ui/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.testproject1"))  // swagger 라는 애가 RestController 를 다 스캔 할 것인데 그 범위를 베이스 패키지(루트 패키지)를 설정
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Around Hub Open API Test with Swagger")
                .version(API_VERSION)  // maven 에서 설정한 버전
                .description(API_DESCRIPTION)
                .build();  // 설정 적용
    }
}
