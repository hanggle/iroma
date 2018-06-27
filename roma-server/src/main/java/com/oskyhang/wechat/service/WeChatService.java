package com.oskyhang.wechat.service;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/6/10 <br/>
 */
public class WeChatService {
    private static final String APPID="wxac8561a0f5021639";
    private static final String APPSECRET="606eee037f460f9f6185b0b015b1745a";

    private static final String ACCESS_TOKEN="";
    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //?grant_type=client_credential&appid=APPID&secret=APPSECRET
    public String getAccessToken(){
        // HttpClientUtil.post();
        return "";
    }

}
