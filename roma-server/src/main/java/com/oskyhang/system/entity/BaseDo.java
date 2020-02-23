package com.oskyhang.system.entity;

import lombok.Data;

import java.util.Date;

/**
 * 实体基类
 *
 * @author hanggle
 * @date 2020/2/23
 */

@Data
public class BaseDo {

    private Long id;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人ID
     */
    private Long createBy;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改时间
     */
    private Long modifyBy;
}
