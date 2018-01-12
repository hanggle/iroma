package com.demo.controller;

import com.oskyhang.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:
 * author: Smile
 * date: 2017/4/26
 */
@Controller
public class PageController {

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        System.out.println("-----------------------------------------------------------");
        System.out.println(111);
        System.out.println(222);
        System.out.println("-----------------------------------------------------------");
        return StringUtil.fun("");
    }
}
