package com.bsg.upm.dto;

import java.io.Serializable;
import java.math.BigInteger;

import com.alibaba.fastjson.annotation.JSONField;

public class RemoteStorageDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private SiteDTO site;
	private TypeDTO vendor;
	private String model;
	private TypeDTO type;
	private String ip;
	private Integer port;
	private String username;
	private BigInteger totalSize;
	private BigInteger usedSize;
	private Boolean enabled;
	private String description;
	private AuthDTO auth;
	/**
	 * 创建
	 */
	private InfoDTO created;

	/**
	 * 变更
	 */
	private InfoDTO modified;

	private TaskDTO task;

	public AuthDTO getAuth() {
		return auth;
	}

	public void setAuth(AuthDTO auth) {
		this.auth = auth;
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

	public SiteDTO getSite() {
		return site;
	}

	public void setSite(SiteDTO site) {
		this.site = site;
	}

	public TypeDTO getVendor() {
		return vendor;
	}

	public void setVendor(TypeDTO vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public TypeDTO getType() {
		return type;
	}

	public void setType(TypeDTO type) {
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

	public BigInteger getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(BigInteger totalSize) {
		this.totalSize = totalSize;
	}

	public BigInteger getUsedSize() {
		return usedSize;
	}

	public void setUsedSize(BigInteger usedSize) {
		this.usedSize = usedSize;
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

	public InfoDTO getCreated() {
		return created;
	}

	public void setCreated(InfoDTO created) {
		this.created = created;
	}

	public InfoDTO getModified() {
		return modified;
	}

	public void setModified(InfoDTO modified) {
		this.modified = modified;
	}

	public TaskDTO getTask() {
		return task;
	}

	public void setTask(TaskDTO task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "RemoteStorageDTO [id=" + id + ", name=" + name + ", site=" + site + ", vendor=" + vendor + ", model="
				+ model + ", type=" + type + ", ip=" + ip + ", port=" + port + ", username=" + username + ", totalSize="
				+ totalSize + ", usedSize=" + usedSize + ", enabled=" + enabled + ", description=" + description
				+ ", auth=" + auth + ", created=" + created + ", modified=" + modified + ", task=" + task + "]";
	}

	public class SiteDTO {
		private String id;
		private String name;

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

		@Override
		public String toString() {
			return "SiteDTO [id=" + id + ", name=" + name + "]";
		}

	}
	
	public class AuthDTO {
		@JSONField(name = "ip")
		private String ip;

		@JSONField(name = "port")
		private Integer port;

		@JSONField(name = "username")
		private String username;
		
		@JSONField(name = "vstorename")
		private String vstorename;

		@JSONField(name = "password")
		private String password;

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


		public String getVstorename() {
			return vstorename;
		}

		public void setVstorename(String vstorename) {
			this.vstorename = vstorename;
		}

		@Override
		public String toString() {
			return "Auth [ip=" + ip + ", port=" + port + ", username=" + username + ", vstorename=" + vstorename
					+ ", password=" + password + "]";
		}
		
	}

}
