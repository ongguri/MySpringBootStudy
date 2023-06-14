package com.example.testproject1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  // controller 로 사용되는것을 명시
public class HelloController {

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }
}
