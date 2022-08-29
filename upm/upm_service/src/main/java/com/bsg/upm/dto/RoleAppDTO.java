package com.bsg.upm.dto;

import java.io.Serializable;

public class RoleAppDTO implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private String roleId;
    
    /**
     * 
     */
    private Long appId;

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
    	return "RoleAppDTO [roleId="+roleId+",appId="+appId+"]";
    }
    
}
