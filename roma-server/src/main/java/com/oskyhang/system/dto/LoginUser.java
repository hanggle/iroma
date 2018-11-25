package com.oskyhang.system.dto;

import lombok.Data;

/**
 * @Description: <br/>
 * @Author: zh <br/>
 * @Date: 2018/11/4 <br/>
 */
@Data
public class LoginUser {
    private Long userId;
    private Long personId;
    private String personName;
    private Integer gender;

}
