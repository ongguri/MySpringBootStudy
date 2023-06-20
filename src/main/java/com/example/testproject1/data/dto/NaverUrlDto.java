package com.example.testproject1.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NaverUrlDto {

    private String message;
    private String code;
    private Result result;

    @Getter
    @Setter
    public static class Result { // 내부 클래스. static 을 써야하는가 말아야하는가?
        private String hash;
        private String url;
        private String orgUrl;
    }
}
