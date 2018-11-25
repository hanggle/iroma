package com.oskyhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication  //@SpringBootApplication等价于@Configuration @EnableAutoConfiguration
// @MapperScan("com.oskyhang.*.mapper")// 正式环境需打开，并注释掉DatasourceConfig.java
@ComponentScan(basePackages = {"com.oskyhang", "com.hanggle.frames"})
public class Application {

	public static void main(String[] args) throws Exception {
		//第一种启动方式
		//SpringApplication.run(com.oskyhang.Application.class, args);

		//第二种启动方式自定义配置
		SpringApplication app = new SpringApplication(Application.class);
		// app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
