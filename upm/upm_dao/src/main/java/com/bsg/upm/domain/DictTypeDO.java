package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 字典类型实体类
 * 
 * @author HCK
 * @date 2018年5月8日
 */
public class DictTypeDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典类型项称
     */
    private String name;

    /**
     * 字典类型集合
     */
    private List<DictDO> dicts;

    /**
     * 获取自增主键
     * 
     * @return id 自增主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置自增主键
     * 
     * @param id
     *            自增主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取字典类型编码
     * 
     * @return code 字典类型编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字典类型编码
     * 
     * @param code
     *            字典类型编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取字典类型项称
     * 
     * @return name 字典类型项称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典类型项称
     * 
     * @param name
     *            字典类型项称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取字典类型集合
     * 
     * @return dicts 字典类型集合
     */
    public List<DictDO> getDicts() {
        return dicts;
    }

    /**
     * 设置字典类型集合
     * 
     * @param dicts
     *            字典类型集合
     */
    public void setDicts(List<DictDO> dicts) {
        this.dicts = dicts;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DictTypeDO [id=" + id + ", code=" + code + ", name=" + name + ", dicts=" + dicts + "]";
    }

}
