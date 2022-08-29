package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmBackupStrategyBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "once")
	private Boolean once;
	
	@JSONField(name = "retention")
	private Integer retention;
	
	@JSONField(name = "app_id")
	private String appId;
	
	@JSONField(name = "type")
	private String type;
	
	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "schedule")
	private String schedule;
	
	@JSONField(name = "created_user")
	private String createdUser;
	
	@JSONField(name = "desc")
	private String desc;
	
	@JSONField(name = "storage")
	private String storage;
	
	@JSONField(name = "endpoint_id")
	private String endpointId;
	
	@JSONField(name = "role")
	private String role;
	
	@JSONField(name = "unit_id")
	private String unit_id;

	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUnit_id() {
		return unit_id;
	}


	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}


	public String getEndpointId() {
		return endpointId;
	}


	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}


	public String getStorage() {
		return storage;
	}


	public void setStorage(String storage) {
		this.storage = storage;
	}


	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public Boolean getOnce() {
		return once;
	}
	

	public Boolean getEnabled() {
		return enabled;
	}
	


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	


	public void setOnce(Boolean once) {
		this.once = once;
	}
	

	public Integer getRetention() {
		return retention;
	}
	

	public void setRetention(Integer retention) {
		this.retention = retention;
	}
	

	public String getAppId() {
		return appId;
	}
	

	public void setAppId(String appId) {
		this.appId = appId;
	}
	

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

	public String getSchedule() {
		return schedule;
	}
	

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	

	public String getCreatedUser() {
		return createdUser;
	}
	

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	

	public String getDesc() {
		return desc;
	}
	


	public void setDesc(String desc) {
		this.desc = desc;
	}


	@Override
	public String toString() {
		return "MgmBackupStrategyBody [name=" + name + ", once=" + once + ", retention=" + retention + ", appId="
				+ appId + ", type=" + type + ", enabled=" + enabled + ", schedule=" + schedule + ", createdUser="
				+ createdUser + ", desc=" + desc + ", storage=" + storage + ", endpointId=" + endpointId + ", role="
				+ role + ", unit_id=" + unit_id + "]";
	}

}
