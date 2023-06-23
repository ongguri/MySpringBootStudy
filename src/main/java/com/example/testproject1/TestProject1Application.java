package com.example.testproject1;

import com.example.testproject1.config.ProfileManager;
import com.example.testproject1.config.env.EnvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestProject1Application {

	private final Logger LOGGER = LoggerFactory.getLogger(TestProject1Application.class);

	@Autowired
	public TestProject1Application(EnvConfiguration envConfiguration, ProfileManager profileManager) {
		LOGGER.info(envConfiguration.getMessage());
		profileManager.printActiveProfiles();
	} // 의존 주입을 해줌으로써, properties 파일에 spring.profiles.active 로 활성화를 해주지 않으면 envConfiguration 이 구현체가 없다고 오류 발생.
	// Edit configuration 에 active profiles 부분에 profile 명(dev 인지 local 인지)를 작성하면 해당 properties 활성화
	public static void main(String[] args) {
		SpringApplication.run(TestProject1Application.class, args);
	}

}
