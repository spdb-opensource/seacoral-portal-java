package com.bsg.upm.mgm.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmSite extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "domain")
	private String domain;

	@JSONField(name = "port")
	private Integer port;

	@JSONField(name = "version")
	private String version;

	@JSONField(name = "state")
	private String state;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "region")
	private String region;

	@JSONField(name = "created")
	private Info created;

	@JSONField(name = "modified")
	private Info modified;
	
	@JSONField(name = "image_registry")
	private String image_registry;
	
	public String getImage_registry() {
		return image_registry;
	}

	public void setImage_registry(String image_registry) {
		this.image_registry = image_registry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Info getCreated() {
		return created;
	}

	public void setCreated(Info created) {
		this.created = created;
	}

	public Info getModified() {
		return modified;
	}

	public void setModified(Info modified) {
		this.modified = modified;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "MgmSite [id=" + id + ", name=" + name + ", type=" + type + ", domain=" + domain + ", port=" + port
				+ ", version=" + version + ", state=" + state + ", desc=" + desc + ", region=" + region + ", created="
				+ created + ", modified=" + modified + ", image_registry=" + image_registry + "]";
	}

}
