package com.oskyhang.system.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 登录用户<br />
 * @Author: zh <br/>
 * @Date: 2018/11/4 <br/>
 */
@Data
@ToString(callSuper = true)
public class BdUser extends BaseDo implements Serializable {

    /**
     * 姓名
     */
    private String name;
    /**
     * 姓名
     */
    private String username;
    /**
     * 手机号
     */
    private String cellphone;

    /**
     * 用户名
     */
    private String gender;
    /**
     * 城市code
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;
}
