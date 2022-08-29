package com.bsg.upm.mgm.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.query.BackupEndpointParam.Config;

public class MgmBackupFile extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "site")
	private IdAndName site;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "unit")
	private IdAndName unit;

	@JSONField(name = "app")
	private IdAndName app;

	@JSONField(name = "backup_storage")
	private BackupStorage backupStorage;

	@JSONField(name = "path")
	private String path;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "size")
	private Long size;

	@JSONField(name = "retention")
	private Integer retention;

	@JSONField(name = "create_at")
	private String createdAt;

	@JSONField(name = "finish_at")
	private String finishedAt;
	
	@JSONField(name = "expire_at")
	private String expireAt;

	@JSONField(name = "valid")
	private Boolean vaild;
	
	@JSONField(name = "status")
	private String status;


	@JSONField(name = "created_user")
	private String createdUser;
	
	@JSONField(name = "enabled")
	private String enabled;
	
	@JSONField(name = "site_id")
	private String siteId;
	
	@JSONField(name = "config")
	private Config config;
	
	@JSONField(name = "endpoint_type")
	private String endpointType;
	
	@JSONField(name = "endpoint")
	private IdAndName endpoint;

	public String getEndpointType() {
		return endpointType;
	}

	public void setEndpointType(String endpointType) {
		this.endpointType = endpointType;
	}

	public IdAndName getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(IdAndName endpoint) {
		this.endpoint = endpoint;
	}

	public IdAndName getSite() {
		return site;
	}

	public void setSite(IdAndName site) {
		this.site = site;
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

	public IdAndName getUnit() {
		return unit;
	}

	public void setUnit(IdAndName unit) {
		this.unit = unit;
	}

	public IdAndName getApp() {
		return app;
	}

	public void setApp(IdAndName app) {
		this.app = app;
	}

	public BackupStorage getBackupStorage() {
		return backupStorage;
	}

	public void setBackupStorage(BackupStorage backupStorage) {
		this.backupStorage = backupStorage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getRetention() {
		return retention;
	}

	public void setRetention(Integer retention) {
		this.retention = retention;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(String finishedAt) {
		this.finishedAt = finishedAt;
	}

	public Boolean getVaild() {
		return vaild;
	}

	public void setVaild(Boolean vaild) {
		this.vaild = vaild;
	}

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	
	public String getExpireAt() {
		return expireAt;
	}
	

	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return "MgmBackupFile [site=" + site + ", id=" + id + ", name=" + name + ", unit=" + unit + ", app=" + app
				+ ", backupStorage=" + backupStorage + ", path=" + path + ", type=" + type + ", size=" + size
				+ ", retention=" + retention + ", createdAt=" + createdAt + ", finishedAt=" + finishedAt + ", expireAt="
				+ expireAt + ", vaild=" + vaild + ", status=" + status + ", createdUser=" + createdUser + ", enabled="
				+ enabled + ", siteId=" + siteId + ", config=" + config + ", endpointType=" + endpointType
				+ ", endpoint=" + endpoint + "]";
	}


	public static class IdAndName {
		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
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
			return "IdAndName [id=" + id + ", name=" + name + "]";
		}

	}

	public static class BackupStorage {
		@JSONField(name = "id")
		private String id;

		@JSONField(name = "type")
		private String type;

		@JSONField(name = "name")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "BackupStorage [id=" + id + ", type=" + type + ", name=" + name + "]";
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
