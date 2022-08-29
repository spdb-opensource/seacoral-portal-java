package com.bsg.upm.dao;

import java.util.List;

/**
 * 增删改查接口
 * 
 * @author HCK
 *
 * @param <T>
 */
public interface CrudDAO<T, E> {

    /**
     * 根据检索条件获取所有记录
     * 
     * @param e
     *            请求参数对象
     * @return 符合检索条件的记录集合
     */
    List<T> list(E e);
    
    /**
     * 根据检索条件获取所有记录
     * 
     * @param e
     *            请求参数对象
     * @return 符合检索条件的记录集合
     */
    List<T> listBase(E e);

    /**
     * 获取单条记录
     * 
     * @param id
     *            主键
     * @return 单条记录
     */
    T get(String id);
    
//    /**
//     * 获取单条详细记录
//     * 
//     * @param id
//     *            主键
//     * @return 单条记录
//     */
//    T getDetail(Long id);

    /**
     * 保存记录
     * 
     * @param domain
     *            记录实体类对象
     * @return 保存条数
     */
    int save(T domain);

    /**
     * 更新记录
     * 
     * @param domain
     *            记录实体类对象
     * @return 更新条数
     */
    int update(T domain);

    /**
     * 删除记录
     * 
     * @param id
     *            主键
     * @return 删除条数
     */
    int remove(String name);
    
    /**
     * 删除记录
     * 
     * @param id
     *            主键
     * @return 删除条数
     */
    int removeDeleted(String name);
}
