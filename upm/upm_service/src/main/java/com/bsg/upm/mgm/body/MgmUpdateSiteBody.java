package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmUpdateSiteBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "domain")
	private String domain;

	@JSONField(name = "port")
	private Integer port;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "region")
	private String region;

	@JSONField(name = "modified_user")
	private String modifiedUser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}
	

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	@Override
	public String toString() {
		return "MgmUpdateSiteBody [name=" + name + ", domain=" + domain + ", port=" + port + ", desc=" + desc
				+ ", region=" + region + ", modifiedUser=" + modifiedUser + "]";
	}
	


}
