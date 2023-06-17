package com.example.testproject1.service.impl;

import com.example.testproject1.dto.MemberDTO;
import com.example.testproject1.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getAroundHub() {

        // uri : 어떤 경로를 요청할 것인지에 사용되는 클래스
        // 만드는 방법은 다양하다.
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")  // 어떤 경로인지 : 해당 예시는 8080이 아닌 8081을 사용하였다.
                .path("/api/server/around-hub")  // 뒤에 붙는 경로는 어떤지
                .encode()  // 기본적인 인코딩 utf-8
                .build()  // 설정한 값을 넣고
                .toUri();  // uri 값으로 변경

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/name")
                .queryParam("name", "sun_yong")  // RequestParam
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/path-variable/{name}")
                .encode()
                .build()
                .expand("sun_yong") // 복수의 값을 넣어야 할 경우 , 를 추가하여 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/member")
                .queryParam("name", "sun_yong")
                .queryParam("email", "rkd@naver.com")
                .queryParam("organization", "naver")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("sun Yong!!");
        memberDTO.setEmail("rkd@naver.com");
        memberDTO.setOrganization("naver!!");
        /*
        serverBox 프로젝트 파일에 있는 getMember() 속 주석처리 된 코드가 주석처리가 되어있냐 안되어있냐에 따라
        new MemberDTO() 로 생성한 값이 호출되는지, uri 로 설정한 값이 호출되는지가 달라진다.
         */

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDTO> addHeader() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("sun Yong!!");
        memberDTO.setEmail("rkd@naver.com");
        memberDTO.setOrganization("naver!!");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)  // post 메서드로 사용
                .header("around-header", "Around Hub Studio")  // header 의 키와 value 값
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}
