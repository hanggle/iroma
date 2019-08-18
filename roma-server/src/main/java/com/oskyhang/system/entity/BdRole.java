package com.oskyhang.system.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BdRole {
    private Long id;

    private String role;

    private String description;

    private Boolean available;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;

}