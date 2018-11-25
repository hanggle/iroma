package com.hanggle.frames.base;

import java.util.List;
import java.util.Map;

/**
 * @Description: mapper 基类<br/>
 * @Author: zh <br/>
 * @Date: 2018/11/3 <br/>
 */
public interface BaseMapper<T> {
    /**
     * 删除
     * @param id id
     * @return id
     */
    int delete(Long id);

    /**
     * 新增
     * @param object 实体
     * @return id
     */
    int insert(T object);

    /**
     * 根据id选择
     * @param id id
     * @return 查询的对象
     */
    T load(Long id);

    /**
     * 根据id动态更新
     * @param object 对象
     * @return id
     */
    int updateSelective(T object);

    /**
     *  据id更新
     * @param record  对象
     * @return id
     */
    int update(T record);

    /**
     *  据id更新
     * @param params  对象
     * @return id
     */
    List<T> list(Map params);

}
