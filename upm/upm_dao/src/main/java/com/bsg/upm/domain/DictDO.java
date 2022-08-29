package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典实体类
 * 
 * @author HCK
 * @date 2018年5月8日
 */
public class DictDO implements Serializable {

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
    private String dictTypeCode;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sequence;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 修改者
     */
    private String editor;

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
     * @return dictTypeCode 字典类型编码
     */
    public String getDictTypeCode() {
        return dictTypeCode;
    }

    /**
     * 设置字典类型编码
     * 
     * @param dictTypeCode
     *            字典类型编码
     */
    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    /**
     * 获取字典编码
     * 
     * @return code 字典编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字典编码
     * 
     * @param code
     *            字典编码
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

    /**
     * 获取显示顺序
     * 
     * @return sequence 显示顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置显示顺序
     * 
     * @param sequence
     *            显示顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取创建时间
     * 
     * @return gmtCreate 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     * 
     * @param gmtCreate
     *            创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取创建者
     * 
     * @return creator 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     * 
     * @param creator
     *            创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取修改时间
     * 
     * @return gmtModified 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     * 
     * @param gmtModified
     *            修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取修改者
     * 
     * @return editor 修改者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置修改者
     * 
     * @param editor
     *            修改者
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DictDO [id=" + id + ", dictTypeCode=" + dictTypeCode + ", code=" + code + ", name=" + name
                + ", sequence=" + sequence + ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", gmtModified="
                + gmtModified + ", editor=" + editor + "]";
    }

}
