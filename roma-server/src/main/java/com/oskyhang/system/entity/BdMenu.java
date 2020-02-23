package com.oskyhang.system.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: <br/>
 * @Author: zh <br/>
 * @Date: 2018/11/4 <br/>
 */
@Data
@ToString(callSuper = true)
public class BdMenu extends BaseDo implements Serializable {
    private static final long serialVersionUID = -221896583108389192L;

    private String name;

    private String path;

    private Long parentId;

    private Integer orderCode;

    private String icon;

    private String title;

    private String description;

    private Integer level;
}
