package com.frames.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.Validate.notEmpty;

/**
 * @Description: springbean 工具类<br/>
 * @Author: zh <br/>
 * @Date: 2018/10/15 <br/>
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtils.context = context;
    }


    public static Object getBean(String beanName) {
        notEmpty(beanName, "bean name is required");
        return context==null?null:context.getBean(beanName);
    }

    public static<T> T getBeanByType(Class<T> tClass) {
        return context.getBean(tClass);

    }
}
