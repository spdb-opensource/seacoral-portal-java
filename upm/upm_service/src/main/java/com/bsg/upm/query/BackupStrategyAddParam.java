package com.bsg.upm.query;

import java.io.Serializable;

public class BackupStrategyAddParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Boolean enabled;
	private String type;
	private String description;
	private Integer retention;
	private String cronExpression;
	private String backupFileId;
	private String storage;
	private String endpointId;
	private String role;
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
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getRetention() {
		return retention;
	}
	
	public void setRetention(Integer retention) {
		this.retention = retention;
	}
	
	public String getCronExpression() {
		return cronExpression;
	}
	
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	public String getBackupFileId() {
		return backupFileId;
	}
	
	public void setBackupFileId(String backupFileId) {
		this.backupFileId = backupFileId;
	}
	

	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BackupStrategyAddParam [name=" + name + ", enabled=" + enabled + ", type=" + type + ", description="
				+ description + ", retention=" + retention + ", cronExpression=" + cronExpression + ", backupFileId="
				+ backupFileId + ", storage=" + storage + ", endpointId=" + endpointId + ", role=" + role + ", unit_id="
				+ unit_id + "]";
	}
	
	
}