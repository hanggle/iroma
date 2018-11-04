package com.oskyhang.system.dto;

import lombok.Data;

@Data
public class BdMenuDto {
    private Long id;

    private String menuName;

    private String sname;

    private String path;

    private Long parentId;

    private String menuType;

    private Integer orderCode;

    private String icon;

    private String param;

    private String menuCode;

    private String isMenu;

    private String status;

    private String flag;

    private String title;

    private String component;

    private String description;

    private String redirect;

    private String level;

    private String isCache;

}