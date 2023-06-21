package com.example.testproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // jpa Auditing 과 관련된 빈들이 올라오게끔 하는 어노테이션
public class TestProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(TestProject1Application.class, args);
	}

}
