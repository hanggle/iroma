package com.oskyhang.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/5/27 <br/>
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public void check(HttpServletRequest request, HttpServletResponse response, String echostr2){
        String echost = request.getParameter("echostr");
        JSONObject obj = new JSONObject();
        obj.put("status", "1");
        obj.put("desc", "sss");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.append(echost);
        pw.flush();
    }
}
