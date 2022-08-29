package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年8月12日
 */
public class MgmServerGroupUnitBackupBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "app_id")
	private String appId;
	
	@JSONField(name = "name")
	private String name;
	
	@JSONField(name = "once")
	private Boolean once;
	
	@JSONField(name = "unit_id")
	private String unitId;

//	@JSONField(name = "backup_storage_type")
//	private String backupStorageType;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "retention")
	private Integer retention;
	
	@JSONField(name = "schedule")
	private String schedule;

	@JSONField(name = "created_user")
	private String createdUser;
	
	@JSONField(name = "storage")
	private String storage;
	
	@JSONField(name = "desc")
	private String desc;
	
	@JSONField(name = "endpoint_id")
	private String endpointId;

	

	public String getEndpointId() {
		return endpointId;
	}


	public void setEndpointId(String endpintId) {
		this.endpointId = endpintId;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getStorage() {
		return storage;
	}


	public void setStorage(String storage) {
		this.storage = storage;
	}


	public String getAppId() {
		return appId;
	}
	

	public void setAppId(String appId) {
		this.appId = appId;
	}
	

	/*public String getBackupStorageType() {
		return backupStorageType;
	}

	public void setBackupStorageType(String backupStorageType) {
		this.backupStorageType = backupStorageType;
	}*/

	
	
	public String getType() {
		return type;
	}

	public String getUnitId() {
		return unitId;
	}
	


	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	


	public void setOnce(Boolean once) {
		this.once = once;
	}
	


	public String getSchedule() {
		return schedule;
	}
	


	public void setSchedule(String schedule) {
		this.schedule = schedule;
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

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}


	@Override
	public String toString() {
		return "MgmServerGroupUnitBackupBody [appId=" + appId + ", name=" + name + ", once=" + once + ", unitId="
				+ unitId + ", type=" + type + ", retention=" + retention + ", schedule=" + schedule + ", createdUser="
				+ createdUser + ", storage=" + storage + ", desc=" + desc + ", endpintId=" + endpointId + "]";
	}


	
	

}
