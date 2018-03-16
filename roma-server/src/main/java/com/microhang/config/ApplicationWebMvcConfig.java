package com.microhang.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * description:
 * author: Smile
 * date: 2017/4/8
 */
//@Configuration
//@EnableWebMvc
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ApplicationWebMvcConfig.class);

  /*  @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new SqlCostInterceptor());
    }*/

    /*@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/
}
