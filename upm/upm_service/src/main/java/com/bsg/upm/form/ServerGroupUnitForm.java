package com.bsg.upm.form;

import java.io.Serializable;
import java.util.Arrays;

public class ServerGroupUnitForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String storageType;
	private String type;
	private Integer retention;
	private String storage;
	private String name;
	private Boolean once;
	private String schedule;
	private String desc;
	private String endpointId;

	public String getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getOnce() {
		return once;
	}

	public void setOnce(Boolean once) {
		this.once = once;
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

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
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

	@Override
	public String toString() {
		return "ServerGroupUnitForm [storageType=" + storageType + ", type=" + type + ", retention=" + retention
				+ ", storage=" + storage + ", name=" + name + ", once=" + once + ", schedule=" + schedule + ", desc="
				+ desc + ", endpointId=" + endpointId + "]";
	}

}
