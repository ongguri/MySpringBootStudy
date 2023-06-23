package com.example.testproject1.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash(value = "shortUrl", timeToLive = 60)
public class ShortUrlResponseDto implements Serializable { // 직렬화를 위한 인터페이스 구현

    private static final long serialVersionUID = -214490344996507077L;

    @Id // import 확인
    private String orgUrl;
    private String shortUrl;
}
