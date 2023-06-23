package com.example.testproject1.config.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local") // 'local' 환경에서 실행하겠다는 의미
@Configuration
public class LocalConfiguration implements EnvConfiguration {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalConfiguration.class);
    @Value("${around.hub.loading.message}")
    private String message;

    @Override
    @Bean
    public String getMessage() {
        LOGGER.info("[getMessage] LocalConfiguration 입니다.");
        return message;
    }
}
