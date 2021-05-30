package com.oskyhang.system.entity;

import lombok.Data;

@Data
public class SysPermission {
    private Long id;

    private String name;

    private String resourceType;

    private String url;

}