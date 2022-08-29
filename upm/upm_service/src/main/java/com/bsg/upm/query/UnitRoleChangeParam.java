package com.bsg.upm.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UnitRoleChangeParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appId;
	private List<UnitRole> units = new ArrayList<>();
	
	
	public String getAppId() {
		return appId;
	}
	



	public void setAppId(String appId) {
		this.appId = appId;
	}


	public List<UnitRole> getUnits() {
		return units;
	}
	


	public void setUnits(List<UnitRole> units) {
		this.units = units;
	}
	


	@Override
	public String toString() {
		return "UnitRoleChangeParam [appId=" + appId + ", units=" + units + "]";
	}



	public static class UnitRole{
		public UnitRole() {
			
		}
		private String id;
		private String role;
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public String getRole() {
			return role;
		}
		
		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "UnitRole [id=" + id + ", role=" + role + "]";
		}
		
		
	}
	
}