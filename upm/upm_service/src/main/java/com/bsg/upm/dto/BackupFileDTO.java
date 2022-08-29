package com.bsg.upm.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.mgm.model.MgmBackupFile.Config;

public class BackupFileDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String path;
	private String type;
	private Long size;
	private Integer retention;
	private String gmtStart;
	private String gmtEnd;
	private String expire;
	private Boolean valid;
	private String status;
	private IdAndNamDTO storageRemote;
	private IdAndNamDTO unit;
	private ServerGroupDTO servGroup;
	
	private String enabled;
	
	private String siteId;
	
	private Config config;
	private String createdUser;
	private String endpointType;
	private IdAndNamDTO endpoint;

	
	public String getEndpointType() {
		return endpointType;
	}

	public void setEndpointType(String endpointType) {
		this.endpointType = endpointType;
	}

	public IdAndNamDTO getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(IdAndNamDTO endpoint) {
		this.endpoint = endpoint;
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

	public String getExpire() {
		return expire;
	}
	

	public void setExpire(String expire) {
		this.expire = expire;
	}
	

	public void setRetention(Integer retention) {
		this.retention = retention;
	}

	public String getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(String gmtStart) {
		this.gmtStart = gmtStart;
	}

	public String getGmtEnd() {
		return gmtEnd;
	}

	public void setGmtEnd(String gmtEnd) {
		this.gmtEnd = gmtEnd;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}
	

	public IdAndNamDTO getStorageRemote() {
		return storageRemote;
	}

	public void setStorageRemote(IdAndNamDTO storageRemote) {
		this.storageRemote = storageRemote;
	}

	public IdAndNamDTO getUnit() {
		return unit;
	}

	public void setUnit(IdAndNamDTO unit) {
		this.unit = unit;
	}

	public ServerGroupDTO getServGroup() {
		return servGroup;
	}

	public void setServGroup(ServerGroupDTO servGroup) {
		this.servGroup = servGroup;
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

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	@Override
	public String toString() {
		return "BackupFileDTO [id=" + id + ", name=" + name + ", path=" + path + ", type=" + type + ", size=" + size
				+ ", retention=" + retention + ", gmtStart=" + gmtStart + ", gmtEnd=" + gmtEnd + ", expire=" + expire
				+ ", valid=" + valid + ", status=" + status + ", storageRemote=" + storageRemote + ", unit=" + unit
				+ ", servGroup=" + servGroup + ", enabled=" + enabled + ", siteId=" + siteId + ", config=" + config
				+ ", createdUser=" + createdUser + ", endpointType=" + endpointType + ", endpoint=" + endpoint + "]";
	}

	public class IdAndNamDTO {
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
			return "IdAndNamDTO [id=" + id + ", name=" + name + "]";
		}

	}

	public class ServerGroupDTO {
		private String id;
		private String name;
		private String owner;

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

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		@Override
		public String toString() {
			return "ServerGroupDTO [id=" + id + ", name=" + name + ", owner=" + owner + "]";
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
