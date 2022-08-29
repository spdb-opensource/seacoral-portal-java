package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmRemoteStoragePoolBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "native_id")
	private String nativeId;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "performance")
	private String performance;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "created_user")
	private String createdUser;

	@JSONField(name = "modified_user")
	private String modifiedUser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	@Override
	public String toString() {
		return "MgmRemoteStoragePoolBody [name=" + name + ", nativeId=" + nativeId + ", type=" + type + ", performance="
				+ performance + ", enabled=" + enabled + ", desc=" + desc + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}

}
