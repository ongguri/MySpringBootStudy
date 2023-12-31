package com.example.testproject1.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptor.class);

    /*
    컨트롤러로 요청이 가기 전에 수행할 코드를 작성한느 메서드
    return 값이 true 일 경우 컨트롤러로 요청을 전달하고 false 일 경우 컨트롤러로 전달하지 않음
    object handler : 요청을 전달할 컨트롤러 객체가 담겨있음
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("[preHandle] preHandle is performed");
        LOGGER.info("[preHandle] request : {}", request);
        LOGGER.info("[preHandle] request path info : {}", request.getPathInfo());
        LOGGER.info("[preHandle] request header names : {}", request.getHeaderNames());
        LOGGER.info("[preHandle] request URL : {}", request.getRequestURL());
        LOGGER.info("[preHandle] request URI : {}", request.getRequestURI());
        LOGGER.info("[preHandle] request Requested Session Id : {}", request.getRequestedSessionId());

        //TODO HttpServletRequestWrapper 구현하여 Body 값 확인할 수 있게 코드 추가

        return true;
    }

    // 컨트롤러의 로직이 수행된 이후 view 가 렌더링 되기 전에 수행할 코드를 작성하는 메서드
    @Override
    public void  postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("[postHandle] postHandle is performed");
        LOGGER.info("[postHandle] request : {}", request);
        LOGGER.info("[postHandle] response : {}", response);
        LOGGER.info("[postHandle] response : {}", response.getHeaderNames());
    }

    // view 가 렌더링 된 후에 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("[afterCompletion] afterCompletion is performed");
    }
}
