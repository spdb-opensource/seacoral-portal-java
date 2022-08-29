package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmRemoteStorageBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "site_id")
	private String siteId;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "vendor")
	private String vendor;

	@JSONField(name = "model")
	private String model;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "auth")
	private Auth auth;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "created_user")
	private String createdUser;

	@JSONField(name = "modified_user")
	private String modifiedUser;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
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
		return "MgmRemoteStorageBody [siteId=" + siteId + ", name=" + name + ", vendor=" + vendor + ", model=" + model
				+ ", type=" + type + ", enabled=" + enabled + ", desc=" + desc + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}

	public static class Auth {
		@JSONField(name = "ip")
		private String ip;

		@JSONField(name = "port")
		private Integer port;

		@JSONField(name = "username")
		private String username;

		@JSONField(name = "password")
		private String password;
		
		@JSONField(name = "vstorename")
		private String vstorename;

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
			return "Auth [ip=" + ip + ", port=" + port + ", username=" + username + ", password=" + password
					+ ", vstorename=" + vstorename + "]";
		}

	}

}
