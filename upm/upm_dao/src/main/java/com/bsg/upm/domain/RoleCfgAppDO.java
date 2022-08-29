package com.bsg.upm.domain;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年5月17日
 */
public class RoleCfgAppDO implements Serializable {

	 /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;
    
    /**
     * 角色编码
     */
    private String roleId;
    /**
     * 权限编码
     */
    private Long appId;
    
    
    public String getId() {
		return id;
	}
	


	public void setId(String id) {
		this.id = id;
	}
	


	public String getRoleId() {
		return roleId;
	}
	


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	


	public Long getAppId() {
		return appId;
	}
	


	public void setAppId(Long appId) {
		this.appId = appId;
	}
	


	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgAppDO [id=" + id + ", roleId=" + roleId + ", appId=" + appId + "]";
    }
}
