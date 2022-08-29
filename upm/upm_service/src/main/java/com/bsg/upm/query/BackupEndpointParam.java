package com.bsg.upm.query;

import java.io.Serializable;

import javax.print.DocFlavor.STRING;

import com.bsg.upm.mgm.model.MgmBackupStrategy.Config;

public class BackupEndpointParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String site_id;
    private Boolean enabled;
    private String type;
    private String name;
    private String created_user;
    private Config config;


    public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return "BackupEndpointParam [site_id=" + site_id + ", enabled=" + enabled + ", type=" + type + ", name=" + name
				+ ", created_user=" + created_user + ", config=" + config + "]";
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