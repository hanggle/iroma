package com;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication  //@SpringBootApplication等价于@Configuration @EnableAutoConfiguration
//@MapperScan("com.oskyhang.*.mapper")
public class Application {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		System.out.println("hello");

		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		//第一种启动方式
		//SpringApplication.run(com.Application.class, args);

		//第二种启动方式自定义配置可以使用这种方式试试再说吧
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		
	}
}
