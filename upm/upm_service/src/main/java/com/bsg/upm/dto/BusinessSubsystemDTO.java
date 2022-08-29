package com.bsg.upm.dto;

import java.io.Serializable;

public class BusinessSubsystemDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 唯一编码
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
     * 
     */
    private String businessSystemId;

    /**
     * 业务系统名称
     */
    private String businessSystemName;

    /**
     * 所属者
     */
    private String owner;

    /**
     * 所属者
     */
    private String ownerName;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建者姓名
     */
    private String creatorName;

    /**
     * 修改时间
     */
    private String gmtModified;

    /**
     * 修改者
     */
    private String editor;

    /**
     * 修改者姓名
     */
    private String editorName;

    /**
     * 获取唯一编码
     * 
     * @return id 唯一编码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编码
     * 
     * @param id
     *            唯一编码
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
     * 获取业务系统名称
     * 
     * @return businessSystemName 业务系统名称
     */
    public String getBusinessSystemName() {
        return businessSystemName;
    }

    /**
     * 设置业务系统名称
     * 
     * @param businessSystemName
     *            业务系统名称
     */
    public void setBusinessSystemName(String businessSystemName) {
        this.businessSystemName = businessSystemName;
    }

    /**
     * 获取所属者
     * 
     * @return owner 所属者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置所属者
     * 
     * @param owner
     *            所属者
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取所属者
     * 
     * @return ownerName 所属者
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置所属者
     * 
     * @param ownerName
     *            所属者
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 获取创建时间
     * 
     * @return gmtCreate 创建时间
     */
    public String getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     * 
     * @param gmtCreate
     *            创建时间
     */
    public void setGmtCreate(String gmtCreate) {
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
     * 获取创建者姓名
     * 
     * @return creatorName 创建者姓名
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置创建者姓名
     * 
     * @param creatorName
     *            创建者姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 获取修改时间
     * 
     * @return gmtModified 修改时间
     */
    public String getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     * 
     * @param gmtModified
     *            修改时间
     */
    public void setGmtModified(String gmtModified) {
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
     * 获取修改者姓名
     * 
     * @return editorName 修改者姓名
     */
    public String getEditorName() {
        return editorName;
    }

    /**
     * 设置修改者姓名
     * 
     * @param editorName
     *            修改者姓名
     */
    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    @Override
    public String toString() {
        return "BusinessSubsystemDTO [id=" + id + ", name=" + name + ", description=" + description
                + ", businessSystemId=" + businessSystemId + ", businessSystemName=" + businessSystemName + ", owner="
                + owner + ", ownerName=" + ownerName + ", gmtCreate=" + gmtCreate + ", creator=" + creator
                + ", creatorName=" + creatorName + ", gmtModified=" + gmtModified + ", editor=" + editor
                + ", editorName=" + editorName + "]";
    }

}
