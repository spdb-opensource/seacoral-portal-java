package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年7月11日
 */
public class MgmRemoteStorageQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "site_id")
	private String siteId;
	
	public Boolean getEnabled() {
		return enabled;
	}
	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	@JSONField(name = "enabled")
	private Boolean enabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}


	@Override
	public String toString() {
		return "MgmRemoteStorageQuery [name=" + name + ", id=" + id + ", siteId=" + siteId + ", enabled=" + enabled
				+ "]";
	}

	

}
