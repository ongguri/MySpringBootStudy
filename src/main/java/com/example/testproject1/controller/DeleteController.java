package com.example.testproject1.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/del-api")
public class DeleteController {

    // http://localhost:8080/api/v1/del-api/variable1/{String 값}
    @DeleteMapping(value = "/variable1/{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        // 보통 데이터베이스의 값을 삭제하는 역할을 함
        return variable;
    }
}
