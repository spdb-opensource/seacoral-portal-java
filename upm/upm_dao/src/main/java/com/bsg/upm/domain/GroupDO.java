package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 组别实体类
 * 
 * @author HCK
 * @date 2018年5月9日
 */
public class GroupDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;

    /**
     * 组名
     */
    private String name;

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
     * 创建时间
     */
    private Date gmtModified;

    /**
     * 创建者
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
     * 获取组名
     * 
     * @return name 组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     * 
     * @param name
     *            组名
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the 创建时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param 创建时间
     *            the gmtModified to set
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return the 创建者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param 创建者
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
        return "GroupDO [id=" + id + ", name=" + name + ", description=" + description + ", gmtCreate=" + gmtCreate
                + ", creator=" + creator + ", gmtModified=" + gmtModified + ", editor=" + editor + "]";
    }

}
