package com.oskyhang.wechat.entity;

/**
 * description: 微信自定义菜单<br/>
 * author: zh <br/>
 * date: 2018/6/10 <br/>
 */
public class Button {

    private String type;
    private String name;
    private Button[] subButton;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSubButton() {
        return subButton;
    }

    public void setSubButton(Button[] subButton) {
        this.subButton = subButton;
    }
}
