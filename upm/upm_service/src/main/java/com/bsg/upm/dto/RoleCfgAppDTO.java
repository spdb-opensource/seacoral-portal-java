package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RoleCfgAppDTO implements Serializable {
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 角色编码
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;
	

	/**
     * 权限编码
     */
    private List<AppDTO> apps = new ArrayList<>();
    
    public String getRoleId() {
		return roleId;
	}
	

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	

	public String getRoleName() {
		return roleName;
	}
	

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

	
	

	public List<AppDTO> getApps() {
		return apps;
	}
	


	public void setApps(List<AppDTO> apps) {
		this.apps = apps;
	}
	


	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgAppDTO [ roleId=" + roleId + ", roleName=" + roleName + ", apps=" + apps + "]";
    }
    
    public class AppDTO {
        private Long id;

        public Long getId() {
			return id;
		}
		


		public void setId(Long id) {
			this.id = id;
		}
		
		@Override
        public String toString() {
            return "AppDTO [id=" + id + "]";
        }

    }
}
