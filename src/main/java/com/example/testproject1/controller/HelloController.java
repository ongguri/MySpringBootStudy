package com.example.testproject1.controller;

import org.springframework.web.bind.annotation.*;

// 로그 설정을 위한 경로를 임포트
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController  // controller 로 사용되는것을 명시
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    // logback-spring.xml 에서 작성했던 패턴에 %logger 에 들어가는 이름이 HelloController.class 가 되는 것이다.

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/log-test")
    public void logTest() {
        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("error Log");
    }  // 마지막에 JVM running 이 뜨면 제대로 실행 된 것
    // 요청을 보내면 패턴 설정에 따라 출력.
    // thread 는 exec-1 과 같이 thread 의 번호가 나온다.
    // logger 는 HelloController 까지 로거의 항목이 된다. logger{30} 이렇게 설정하면 패키지명이 단축되어 출력된다.
    // 그 뒤에는 로그 메시지 출력. logback-spring.xml 에서 전역설정(root)에 따라 그 위에 단계에 있는 로그들만 나온다.
}
