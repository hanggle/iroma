package com.hanggle.frames.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.repository.query.DefaultParameters;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RomaCacheAspect {

    @Around("@annotation(com.hanggle.frames.annotation.RomaCache)")
    public Object doCache(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("doCache around before");
        // 获取到方法注释里面的key的内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
        RomaCache annotation = method.getAnnotation(RomaCache.class);
        String[] keyArr = annotation.key();
        String prefix = annotation.prefix();

        StringBuilder builder = new StringBuilder();
        // 拿到方法的参数名和参数值，方便解析key表达式
        ExpressionParser parser = new SpelExpressionParser();
        for (int j = 0; j < keyArr.length; j++) {
            String keyEL = keyArr[j];
            Expression expression = parser.parseExpression(keyEL);
            EvaluationContext context = new StandardEvaluationContext();

            Object[] args = joinPoint.getArgs();
            String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);

            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }

            // 解析表达式，生成最终的key
            String key = expression.getValue(context).toString();
            builder.append(key);
        }
        log.info("doCache cache key:{}", prefix + builder.toString());
        Object proceed = joinPoint.proceed();
        log.info("doCache around after");
        return proceed;
    }
}
