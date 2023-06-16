package com.example.testproject1.controller;


import com.example.testproject1.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    // http://localhost:8080/api/v1/put-api/default
    @PutMapping(value = "/default")
    public String putMethod() {
        return "Hello World";
    }

    // http://localhost:8080/api/v1/put-api/member
    /*
    {
      "name" : "kang",
      "email" : "rkdtjsdyd321@naver.com",
      "organization" : "naver"
    }  json 형태로 body에 입력해서 테스트
     */
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:8080/api/v1/put-api/member1
    // @GetMapping 과 다르게 파라미터에 @RequestBody 를 선언해야 한다.
    @PutMapping(value = "/member1")
    public String postMemberDto1(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }

    // http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public MemberDTO postMemberDto2(@RequestBody MemberDTO memberDTO) {
        return memberDTO;
    }

    // http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> postMemberDto3(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);  // ACCEPTED 는 enum 이 202여서 202코드로 response 됨.
        // HttpStatus 형태를 보면 다양한 enum 이 정의되어 있음.
        // body 값으로 memberDTO 를 써서 return memberDTO 와 같은 효과를 나타냄
    }
}
