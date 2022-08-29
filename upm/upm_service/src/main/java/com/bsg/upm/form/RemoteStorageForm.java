package com.bsg.upm.form;

import java.io.Serializable;

public class RemoteStorageForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 站点编码
	 */
	private String siteId;
	private String vendorCode;
	private String name;
	private String type;
	private String ip;
	private Integer port;
	private String username;
	private String password;
	private Boolean enabled;
	private String description;
	private String vstorename;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getVstorename() {
		return vstorename;
	}

	public void setVstorename(String vstorename) {
		this.vstorename = vstorename;
	}

	@Override
	public String toString() {
		return "RemoteStorageForm [siteId=" + siteId + ", vendorCode=" + vendorCode + ", name=" + name + ", type="
				+ type + ", ip=" + ip + ", port=" + port + ", username=" + username + ", password=" + password
				+ ", enabled=" + enabled + ", description=" + description + ", vstorename=" + vstorename + "]";
	}

}
