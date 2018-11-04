package com.oskyhang.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frames.base.BaseController;
import com.frames.base.BaseResult;
import com.frames.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 用户登录模块<br/>
 * author: zh <br/>
 * date: 2018/3/12 <br/>
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class BdUserController extends BaseController {



    @RequestMapping(value = "/info" , method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<JSONObject> test(HttpServletRequest request, HttpServletResponse response){

        JSONObject obj = new JSONObject();
        String [] roles = {"/documentation", "index",  "/permission", "admin"};
        obj.put("roles", roles);
        obj.put("name", "tom");
        obj.put("username", "admin");
        obj.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        obj.put("introduction", "this is introduction");
        return Response.success(obj);
    }

    @RequestMapping(value = "list", method= RequestMethod.GET)
    public String select(String id, String data) {
        JSONArray arr = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("author", "Gary");
        obj.put("comment_disabled", true);
        obj.put("content", "<p>我是测试数据我是测试数据</p><p><img src=\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\"></p>");
        obj.put("content_short", "我是测试数据");
        obj.put("display_time", "2013-12-08 18:17:23");
        obj.put("forecast", 46.82);
        obj.put("id", 1);
        obj.put("image_uri", "https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3");
        obj.put("importance", 2);
        obj.put("pageviews", 756);
        obj.put("reviewer", "Robert");
        obj.put("status", "published");
        obj.put("timestamp", 160381459952L);
        obj.put("title", "Yggsquww Emht Oblxure Mxzlvnl Gbfxrd Whiryagkkb Kcjyvtsw");

        arr.add(obj);
        JSONObject obj2 = new JSONObject();
        obj2.put("id", 2);
        obj2.put("author", "Gary");
        obj2.put("comment_disabled", true);
        obj2.put("content", "<p>我是测试数据我是测试数据</p><p><img src=\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\"></p>");
        obj2.put("content_short", "我是测试数据");
        obj2.put("display_time", "2013-12-08 18:17:23");
        obj2.put("forecast", 46.82);
        obj2.put("image_uri", "https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3");
        obj2.put("importance", 2);
        obj2.put("pageviews", 756);
        obj2.put("reviewer", "Robert");
        obj2.put("status", "published");
        obj2.put("timestamp", 160381459952L);
        obj2.put("title", "Yggsquww Emht Oblxure Mxzlvnl Gbfxrd Whiryagkkb Kcjyvtsw");

        arr.add(obj2);

        JSONObject result = new JSONObject();
        result.put("total", arr.size());
        result.put("items", arr);

        return JSONObject.toJSONString(result);
    }
}

