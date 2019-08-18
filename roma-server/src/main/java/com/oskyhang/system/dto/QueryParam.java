package com.oskyhang.system.dto;

import lombok.Data;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/25
 */


@Data
public class QueryParam {
    private Integer pageNo;
    private Integer pageSize;
    private Long id;
    private Long name;
}
