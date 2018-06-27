package com.frames.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * description: spring AOP
 * author: Smile
 * date: 2017/4/23
 */
//@Aspect
//@Component
public class AspectClass {

    private final static Logger logger = LoggerFactory.getLogger(AspectClass.class);

    @Pointcut("execution(public * com.oskyhang.*.handler.*.*(..))")
    public void pointCut(){
        logger.debug("this is pointCut");
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        logger.debug("this is before");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获去地址
        logger.debug("url={}", request.getRequestURL());
        //ip
        logger.debug("ip={}",request.getRemoteAddr());
        //类方法
        logger.debug("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.debug("args={}", joinPoint.getArgs());

    }

    @After("pointCut()")
    public void doAfter(){
        logger.debug("this is after");
    }

    @AfterReturning(returning = "object", pointcut = "pointCut()")
    public void doReturn(Object object){
        logger.debug("response{}", object.toString() );
    }
}
