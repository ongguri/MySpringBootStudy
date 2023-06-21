package com.example.testproject1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // jpaAuditing 기능을 활성화 하겠다는 의미. 사실 TestProject1Application 클래스에 붙여도 정상 작동하지만,
// 강의에서는 따로 configuration 클래스를 만들어서 활성화 하는 것을 추천
// jpa 객체가 없는데도 전체가 jpaAuditing 이 활성화 되는 바람에 test 들이 정상적으로 동작하지 않는 경우가 발생하기 때문
public class JpaAuditingConfiguration {

}
