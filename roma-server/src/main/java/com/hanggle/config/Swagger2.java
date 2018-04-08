package com.hanggle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * description: API <br/>
 * author: zh <br/>
 * date: 2018/4/8 <br/>
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.oskyhang.*.handler"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IRoma RESTful APIs")
                .description("go go go")
                .termsOfServiceUrl("http://blog.hanggle.com/")
                .contact(new Contact("hanggle", "http://blog.hanggle.com/", "hanggle@qq.com"))
                .version("1.0")
                .build();
    }
}