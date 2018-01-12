package com.demo.controller;

import com.demo.entity.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 相当于@Controller和@ResponseBody
public class TestController {

	@Autowired
	private MyProperties p;

	@RequestMapping(value = "/demo", method = RequestMethod.POST)
	String getdemo() {
		String cnName = p.getCnName();
		System.out.println("{{{{ LoginController.getdemo(): cnname值 = " + cnName + " }}}}");
		return "getdemo2";
	}

	//访问 url1、url2 均可
	@RequestMapping(value={"/url1","/url2"}, method = RequestMethod.GET)
	String getdemo2() {
		String cnName = p.getCnName();
		System.out.println("{{{{ LoginController.getdemo(): cnname值 = " + cnName + " }}}}");
		return "123";
	}

	//获去请求路径中的值
	@RequestMapping(value="/urlPath/{id}", method = RequestMethod.GET)
	String urlPath(@PathVariable("id") String id) {
		System.out.println("*******************************************************");
		System.out.println("{{{{ LoginController.getdemo(): id = " + id + " }}}}");
		System.out.println("*******************************************************");
		return id;
	}
	//获去请求参数中的值
	@RequestMapping(value="/urlParam", method = RequestMethod.GET)
	String urlParam(@RequestParam("id") String id) {
		System.out.println("*******************************************************");
		System.out.println("{{{{ LoginController.getdemo(): id = " + id + " }}}}");
		System.out.println("*******************************************************");

		return id;
	}
	//获去请求参数中的值,设置默认值
	//@RequestMapping(value="/urlParam2", method = RequestMethod.GET)
	@RequestMapping("/test2")  //等同于@RequestMapping(value="/urlParam2", method = RequestMethod.GET)
	String test2(@RequestParam(value = "id", required = false, defaultValue = "default value") String id) {
		System.out.println(123);
		return "/html/hello.html";
	}
}
