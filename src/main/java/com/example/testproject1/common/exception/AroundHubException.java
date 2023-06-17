package com.example.testproject1.common.exception;

import com.example.testproject1.common.Constants;
import org.springframework.http.HttpStatus;

public class AroundHubException extends Exception {

    private static final long serialVersionUID = 4663380430591151694L;  // 직렬화를 위함.
    // 직렬화 : 메모리를 디스크에 저장하거나 네트워크 통신에 사용하기 위한 형식으로 변환하는 것을 말한다.

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public AroundHubException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();  // httpStatus 를 따라 가면 public int value() 메서드가 존재해서 값 리턴
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
