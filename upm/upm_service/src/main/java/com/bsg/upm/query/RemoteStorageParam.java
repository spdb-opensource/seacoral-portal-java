package com.bsg.upm.query;

import java.io.Serializable;

public class RemoteStorageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 站点编码
	 */
	private String siteId;
	
	/**
	 * 是否启用
	 */
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}
	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteStorageParam [siteId=" + siteId + ", enabled=" + enabled + "]";
	}

}
