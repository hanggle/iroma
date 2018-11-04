package com.frames.base;

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
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     * @param record 试题
     * @return id
     */
    int insert(T record);

    /**
     * 动态新增
     * @param record 试题
     * @return id
     */
    int insertSelective(T record);

    /**
     * 根据id选择
     * @param id id
     * @return 查询的对象
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据id动态更新
     * @param record 对象
     * @return id
     */
    int updateByPrimaryKeySelective(T record);

    /**
     *  据id更新
     * @param record  对象
     * @return id
     */
    int updateByPrimaryKey(T record);

}
