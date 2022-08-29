package com.bsg.upm.form;

import java.io.Serializable;

public class DictForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型代码
     */
    private String dictTypeCode;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sequence;

    /**
     * 类型项代码
     */
    private String code;

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

    /**
     * 获取顺序
     * 
     * @return sequence 顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置顺序
     * 
     * @param sequence
     *            顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return the 类型项代码
     */
    public String getCode() {
        return code;
    }

    /**
     * @param 类型项代码
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the 类型代码
     */
    public String getDictTypeCode() {
        return dictTypeCode;
    }

    /**
     * @param 类型代码
     *            the dictTypeCode to set
     */
    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DictForm [dictTypeCode=" + dictTypeCode + ", name=" + name + ", sequence=" + sequence + ", code=" + code
                + "]";
    }

}
