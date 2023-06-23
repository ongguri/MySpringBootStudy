package com.example.testproject1.service.impl;

import com.example.testproject1.data.dao.ShortUrlDAO;
import com.example.testproject1.data.dto.NaverUrlDto;
import com.example.testproject1.data.dto.ShortUrlResponseDto;
import com.example.testproject1.data.entity.ShortUrlEntity;
import com.example.testproject1.data.repository.ShortUrlRedisRepository;
import com.example.testproject1.service.ShortUrlService;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
    private final ShortUrlDAO shortUrlDAO;
    private final ShortUrlRedisRepository shortUrlRedisRepository;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO, ShortUrlRedisRepository shortUrlRedisRepository) {
        this.shortUrlDAO = shortUrlDAO;
        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }
    @Override
    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        LOGGER.info("[getShortUrl] request data : {}", originalUrl);

        // Cache Logic
        Optional<ShortUrlResponseDto> foundResponseDto = shortUrlRedisRepository.findById(originalUrl);
        if(foundResponseDto.isPresent()) {
            LOGGER.info("[getShortUrl] Cache Data is existed.");
            return foundResponseDto.get();  // 캐시가 있을 땐, 밑에 있는 로직이 실행되지 않음
        } else {
            LOGGER.info("[getShortUrl] Cache Data does not existed.");
        }

        ShortUrlEntity getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if (getShortUrlEntity == null) {
            LOGGER.info("[getShortUrl] No Entity in Database.");
            // 값이 없으면 generateShortUrl 의 역할을 수행하게 되므로 리팩토링 과정이 필요.
            ResponseEntity<NaverUrlDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();
        } else {
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        // url 이 어떤 값으로 들어왔는지 로그 찍기
        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        ResponseEntity<NaverUrlDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);
        // requestShortUrl 메서드는 밑에.

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        // Cache Logic
        shortUrlRedisRepository.save(shortUrlResponseDto);
        // redis 는 키와 값의 쌍으로 이뤄져 있는데 Dto 의 @Id 어노테이션이 붙은 값이 키가 되고, shortUrlResponseDto 가 값이 된다.

        LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto.toString());

        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret,
        String originalUrl) {
        return null;
    }

    @Override
    public void deleteShortUrl(String url) {
        if(url.contains("me2.do")) {  // short url 은 me2.do 가 붙는다.
            LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl'.");
            deleteByShortUrl(url);
        } else {
            LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl'.");
            deleteByOriginalUrl(url);
        }
    }

    private void deleteByShortUrl(String url) {
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url) {
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }

    private ResponseEntity<NaverUrlDto> requestShortUrl(String clientId, String clientSecret, String originalUrl) {

        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);

        URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")  // 사용할 API 의 주소
            .path("/v1/util/shorturl")  // 세부 주소
            .queryParam("url", originalUrl)  // queryParam 에 url 추가
            .encode()
            .build()
            .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");  // http request header 를 아래에서 세팅하고 있다는 의미
        HttpHeaders headers = new HttpHeaders();  // 말 그대로 http header 를 꾸며주기 위한 객체
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-id", clientId);  // 필요로 하는 값을 헤더에 담아서 전송해줘야하니 세팅.
        headers.set("X-Naver-client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>("", headers);  // 바디와 헤더를 조합해주는 객체

        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUrlDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                entity, NaverUrlDto.class);

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;

    }
}
