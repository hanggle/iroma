package com.oskyhang.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: 登录用户<br/>
 * @Author: zh <br/>
 * @Date: 2018/11/4 <br/>
 */
@Data
public class BdUser implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String tel;

    private Integer status;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;
}
