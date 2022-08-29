package com.bsg.upm.dto;

import java.io.Serializable;

public class OperateLogDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 操作模块名
     */
    private String objType;

    /**
     * 操作动作
     */
    private String action;

    /**
     * 操作对象名
     */
    private String objName;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 耗时
     */
    private Long uptime;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 站点名称
     */
    private String siteName;

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

    
    public String getObjType() {
		return objType;
	}
	

	public void setObjType(String objType) {
		this.objType = objType;
	}
	

	/**
     * 获取操作动作
     * 
     * @return action 操作动作
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置操作动作
     * 
     * @param action
     *            操作动作
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取操作对象名
     * 
     * @return objName 操作对象名
     */
    public String getObjName() {
        return objName;
    }

    /**
     * 设置操作对象名
     * 
     * @param objName
     *            操作对象名
     */
    public void setObjName(String objName) {
        this.objName = objName;
    }

    /**
     * 获取操作描述
     * 
     * @return description 操作描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置操作描述
     * 
     * @param description
     *            操作描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取耗时
     * 
     * @return uptime 耗时
     */
    public Long getUptime() {
        return uptime;
    }

    /**
     * 设置耗时
     * 
     * @param uptime
     *            耗时
     */
    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    /**
     * 获取是否成功
     * 
     * @return success 是否成功
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 设置是否成功
     * 
     * @param success
     *            是否成功
     */
    public void setSuccess(Boolean success) {
        this.success = success;
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
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取站点名称
     * 
     * @return siteName 站点名称
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 设置站点名称
     * 
     * @param siteName
     *            站点名称
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OperateLogDTO [id=" + id + ", objType=" + objType + ", action=" + action + ", objName=" + objName
                + ", description=" + description + ", uptime=" + uptime + ", success=" + success + ", gmtCreate="
                + gmtCreate + ", creator=" + creator + ", siteId=" + siteId + ", siteName=" + siteName + "]";
    }

}
