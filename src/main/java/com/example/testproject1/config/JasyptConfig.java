package com.example.testproject1.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        String key = "around-hub-studio";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key);  // 암호화할 때 사용하는 키
        config.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘
        config.setKeyObtentionIterations("1000"); // 반복할 해싱 횟수
        config.setPoolSize("1"); // 인스턴스 pool
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스. 암호화할 때마다 랜덤으로 값이 바뀜
//        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");  // 제대로 암호화가 안되는 현상이 있어서 잠시 주석처리
        config.setStringOutputType("base64"); // 인코딩 방식

        encryptor.setConfig(config);
        return encryptor;
    } // jasypt.encryptor.password 말고는 전부 옵션사항이다.
}
