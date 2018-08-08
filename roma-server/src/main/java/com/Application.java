package com;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //@SpringBootApplication等价于@Configuration @EnableAutoConfiguration
// @MapperScan("com.oskyhang.*.mapper")// 正式环境需打开，并注释掉DatasourceConfig.java
public class Application {

	public static void main(String[] args) throws Exception {
		//第一种启动方式
		//SpringApplication.run(com.Application.class, args);

		//第二种启动方式自定义配置可以使用这种方式试试再说吧
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		//以开发配置文件启动
		app.setAdditionalProfiles("dev");
		app.run(args);
	}
}
