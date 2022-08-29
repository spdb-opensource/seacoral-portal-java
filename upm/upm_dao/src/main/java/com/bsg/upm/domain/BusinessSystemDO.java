package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

public class BusinessSystemDO implements Serializable {

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
     * 所属者
     */
    private String owner;

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
     * 子系统名称
     */
    private String businessSubSystemName;

    public String getBusinessSubSystemName() {
		return businessSubSystemName;
	}


	public void setBusinessSubSystemName(String businessSubSystemName) {
		this.businessSubSystemName = businessSubSystemName;
	}


	/**
     * 获取
     * @return id 
     */
    public String getId() {
        return id;
    }
    

    /**
     * 设置
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }
    

    /**
     * 获取名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }
    

    /**
     * 设置名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
    

    /**
     * 获取描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }
    

    /**
     * 设置描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    

    /**
     * 获取所属者
     * @return owner 所属者
     */
    public String getOwner() {
        return owner;
    }
    

    /**
     * 设置所属者
     * @param owner 所属者
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    

    /**
     * 获取创建时间
     * @return gmtCreate 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    

    /**
     * 设置创建时间
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    

    /**
     * 获取创建者
     * @return creator 创建者
     */
    public String getCreator() {
        return creator;
    }
    

    /**
     * 设置创建者
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    

    /**
     * 获取修改时间
     * @return gmtModified 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }
    

    /**
     * 设置修改时间
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
    

    /**
     * 获取修改者
     * @return editor 修改者
     */
    public String getEditor() {
        return editor;
    }
    

    /**
     * 设置修改者
     * @param editor 修改者
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BusinessSystemDO [id=" + id + ", name=" + name + ", description=" + description + ", owner=" + owner
                + ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", gmtModified=" + gmtModified + ", editor="
                + editor + "]";
    }
    

    
}
