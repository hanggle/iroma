package com.hanggle.frames.util;

import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/12/2
 */
@Slf4j
public class PageUtil implements Serializable {
    private static final long serialVersionUID = -598807920539375177L;

    /**
     * 初始化分页参数
     * @param pageNo 页码，前端传值页数从1开始
     * @param pageSize 条数
     * @return map
     */
    public static Map<String, Object> initPageParam(Integer pageNo, Integer pageSize) {
        Map<String, Object> defaultPageMap = new HashMap<>(16);
        if (isNull(pageNo) || isNull(pageSize) || pageNo <= 0 || pageSize <= 0) {
            log.error("Paging parameters are illegal！case:pageNo:{},pageSize:{}", pageNo, pageSize);
            throw new IllegalArgumentException("Paging parameters are illegal！case:pageNo:" + pageNo + ",pageSize:" + pageSize);
        }
        pageNo = pageSize * (pageNo-1);
        defaultPageMap.put("offset", pageNo);
        defaultPageMap.put("limit", pageSize);
        return defaultPageMap;
    }


}
