package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类
 * 
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sequence;

    /**
     * 描述
     */
    private String description;

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
     * 获取角色名称
     * 
     * @return name 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     * 
     * @param name
     *            角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the 顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * @param 顺序
     *            the sequence to set
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取描述
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     *            描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param 修改时间
     *            the gmtModified to set
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return the 修改者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param 修改者
     *            the editor to set
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
        return "RoleDO [id=" + id + ", name=" + name + ", sequence=" + sequence + ", description=" + description
                + ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", gmtModified=" + gmtModified + ", editor="
                + editor + "]";
    }

}
