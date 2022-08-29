package com.bsg.upm.dto;

import java.io.Serializable;

public class DictTypeDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;

    /**
     * 字典代码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

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
     * 获取字典代码
     * 
     * @return code 字典代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字典代码
     * 
     * @param code
     *            字典代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取字典名称
     * 
     * @return name 字典名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典名称
     * 
     * @param name
     *            字典名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DictTypeDTO [id=" + id + ", code=" + code + ", name=" + name + "]";
    }

}
