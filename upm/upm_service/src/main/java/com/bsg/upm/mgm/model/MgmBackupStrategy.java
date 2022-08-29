package com.bsg.upm.mgm.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmBackupStrategy extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "storage")
	private String storage;

	@JSONField(name = "app_id")
	private String appId;

	@JSONField(name = "unit_id")
	private String unitId;

	@JSONField(name = "schedule")
	private String schedule;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "role")
	private String role;
	
	@JSONField(name = "enabled")
	private Boolean enabled;
	
	@JSONField(name = "retention")
	private Integer retention;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "tables")
	private String tables;
	
	@JSONField(name = "created")
	private Created created;

	@JSONField(name = "modified")
	private Created modified;
	
	
	@JSONField(name = "site_id")
	private Created siteId;
	
	@JSONField(name = "config")
	private Config config;

	public Created getSiteId() {
		return siteId;
	}


	public void setSiteId(Created siteId) {
		this.siteId = siteId;
	}


	public Config getConfig() {
		return config;
	}


	public void setConfig(Config config) {
		this.config = config;
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
	

	public String getUnitId() {
		return unitId;
	}
	

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	

	public String getRole() {
		return role;
	}
	

	public void setRole(String role) {
		this.role = role;
	}
	

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

	public String getTables() {
		return tables;
	}
	

	public void setTables(String tables) {
		this.tables = tables;
	}
	

	


	public Created getCreated() {
		return created;
	}
	


	public void setCreated(Created created) {
		this.created = created;
	}
	


	public Created getModified() {
		return modified;
	}
	


	public void setModified(Created modified) {
		this.modified = modified;
	}
	
	
	public Boolean getEnabled() {
		return enabled;
	}
	


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	


	public Integer getRetention() {
		return retention;
	}
	


	public void setRetention(Integer retention) {
		this.retention = retention;
	}
	

	@Override
	public String toString() {
		return "MgmBackupStrategy [id=" + id + ", name=" + name + ", storage=" + storage + ", appId=" + appId
				+ ", unitId=" + unitId + ", schedule=" + schedule + ", desc=" + desc + ", role=" + role + ", enabled="
				+ enabled + ", retention=" + retention + ", type=" + type + ", tables=" + tables + ", created="
				+ created + ", modified=" + modified + ", siteId=" + siteId + ", config=" + config + "]";
	}



	public class Created{
		private String user;
		private String timestamp;
		public String getUser() {
			return user;
		}
		
		public void setUser(String user) {
			this.user = user;
		}
		
		public String getTimestamp() {
			return timestamp;
		}
		
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			return "created [user=" + user + ", timestamp=" + timestamp + "]";
		}
		
		
	}
	
	public class Config{
		private String nfs_ip;
		private String nfs_opts;
		private String nfs_source;
		private String s3_url;
		private String s3_bucket;
		private String s3_access_key;
		private String s3_secret_key;
		public String getNfs_ip() {
			return nfs_ip;
		}
		public void setNfs_ip(String nfs_ip) {
			this.nfs_ip = nfs_ip;
		}
		public String getNfs_opts() {
			return nfs_opts;
		}
		public void setNfs_opts(String nfs_opts) {
			this.nfs_opts = nfs_opts;
		}
		public String getNfs_source() {
			return nfs_source;
		}
		public void setNfs_source(String nfs_source) {
			this.nfs_source = nfs_source;
		}
		public String getS3_url() {
			return s3_url;
		}
		public void setS3_url(String s3_url) {
			this.s3_url = s3_url;
		}
		public String getS3_bucket() {
			return s3_bucket;
		}
		public void setS3_bucket(String s3_bucket) {
			this.s3_bucket = s3_bucket;
		}
		public String getS3_access_key() {
			return s3_access_key;
		}
		public void setS3_access_key(String s3_access_key) {
			this.s3_access_key = s3_access_key;
		}
		public String getS3_secret_key() {
			return s3_secret_key;
		}
		public void setS3_secret_key(String s3_secret_key) {
			this.s3_secret_key = s3_secret_key;
		}
		@Override
		public String toString() {
			return "Config [nfs_ip=" + nfs_ip + ", nfs_opts=" + nfs_opts + ", nfs_source=" + nfs_source + ", s3_url="
					+ s3_url + ", s3_bucket=" + s3_bucket + ", s3_access_key=" + s3_access_key + ", s3_secret_key="
					+ s3_secret_key + "]";
		}
		
	}
}
