package com.oskyhang.system.entity;

import lombok.Data;

import java.util.Date;
/**
 * @Description: <br/>
 * @Author: zh <br/>
 * @Date: 2018/11/4 <br/>
 */
@Data
public class BdMenu {
    private Long id;

    private String name;

    private String path;

    private Long parentId;

    private String menuId;

    private Integer orderCode;

    private String icon;

    private String title;

    private String description;

    private Boolean level;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;

}
