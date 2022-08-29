package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.DictDO;

/**
 * 字典接口
 * 
 * @author HCK
 * @date 2018年5月7日
 */
public interface DictDAO extends CrudDAO<DictDO, Object> {
    
    /**
     * 
     * @param dictTypeCode
     * @return
     */
    List<DictDO> listByDictTypeCode(String dictTypeCode);

    /**
     * 获取指定字典编码和字典类型编码的字典名
     * 
     * @param code
     *            字典编码
     * @param dictTypeCode
     *            字典类型编码
     * @return 符合条件的字典名
     */
    String getNameByCodeAndDictTypeCode(@Param("code") String code, @Param("dictTypeCode") String dictTypeCode);

    /**
     * 获取指定字典编码和字典类型编码的字典个数
     * 
     * @param code
     *            字典编码
     * @param dictTypeCode
     *            字典类型编码
     * @return 符合条件的字典个数
     */
    int countByCodeAndDictTypeCode(@Param("code") String code, @Param("dictTypeCode") String dictTypeCode);

    /**
     * 获取指定字典名和字典类型编码的字典个数
     * 
     * @param name
     *            字典名
     * @param dictTypeCode
     *            字典类型编码
     * @return 符合条件的字典个数
     */
    int countByNameAndDictTypeCode(@Param("name") String name, @Param("dictTypeCode") String dictTypeCode);

    /**
     * 删除指定字典类型编码的字典
     * 
     * @param dictTypeCode
     *            字典类型编码
     * @return 删除条数
     */
    int removeByDictTypeCode(String dictTypeCode);
}
