package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.query.UnitRoleChangeParam.UnitRole;

/**
 * 
 * @author swn
 * @date 2019年8月12日
 */
public class MgmServerGroupUnitRoleChangeSlaveBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "role")
	private String role;
	
	@JSONField(name = "master_id")
	private String masetrId;

	public String getRole() {
		return role;
	}
	

	public void setRole(String role) {
		this.role = role;
	}
	

	public String getMasetrId() {
		return masetrId;
	}
	

	public void setMasetrId(String masetrId) {
		this.masetrId = masetrId;
	}


	@Override
	public String toString() {
		return "MgmServerGroupUnitRoleChangeSlaveBody [role=" + role + ", masetrId=" + masetrId + "]";
	}
	

	
	
}
