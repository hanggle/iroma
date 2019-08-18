package com.hanggle.frames.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/8/18
 */
@Slf4j

public class ErrorInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(response.getStatus()==500){
            modelAndView.setViewName("/errorpage/500");
        }else if(response.getStatus()==404){
            modelAndView.setViewName("/errorpage/404");
        }

    }
}
