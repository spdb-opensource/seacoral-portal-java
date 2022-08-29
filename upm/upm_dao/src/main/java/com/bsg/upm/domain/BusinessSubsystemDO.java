package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

public class BusinessSubsystemDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 业务系统编码
     */
    private String businessSystemId;

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
     * 所属业务系统
     */
    private BusinessSystemDO businessSystem;

    /**
     * 获取
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取名称
     * 
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *            名称
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
     * 获取业务系统编码
     * 
     * @return businessSystemId 业务系统编码
     */
    public String getBusinessSystemId() {
        return businessSystemId;
    }

    /**
     * 设置业务系统编码
     * 
     * @param businessSystemId
     *            业务系统编码
     */
    public void setBusinessSystemId(String businessSystemId) {
        this.businessSystemId = businessSystemId;
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

    /**
     * 获取所属业务系统
     * 
     * @return businessSystem 所属业务系统
     */
    public BusinessSystemDO getBusinessSystem() {
        return businessSystem;
    }

    /**
     * 设置所属业务系统
     * 
     * @param businessSystem
     *            所属业务系统
     */
    public void setBusinessSystem(BusinessSystemDO businessSystem) {
        this.businessSystem = businessSystem;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BusinessSubsystemDO [id=" + id + ", name=" + name + ", description=" + description
                + ", businessSystemId=" + businessSystemId + ", gmtCreate=" + gmtCreate + ", creator=" + creator
                + ", gmtModified=" + gmtModified + ", editor=" + editor + ", businessSystem=" + businessSystem + "]";
    }

}
