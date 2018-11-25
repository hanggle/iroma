package com.oskyhang.system.entity;

import lombok.Data;

import java.util.Date;
/**
 * @Description: 登录用户<br/>
 * @Author: zh <br/>
 * @Date: 2018/11/4 <br/>
 */
@Data
public class BdUser {
    private Long id;

    private String loginName;

    private String loginPwd;

    private String email;

    private String tel;

    private String personId;

    private Integer status;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;
}
