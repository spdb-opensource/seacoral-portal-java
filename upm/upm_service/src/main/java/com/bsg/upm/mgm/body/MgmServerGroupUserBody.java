package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmServerGroupUserBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "ip")
	private String ip;

	@JSONField(name = "auth_type")
	private String authType;

	@JSONField(name = "pwd")
	private String pwd;

	@JSONField(name = "db_privileges")
	private Privilege[] db_privileges;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	

	public String getPwd() {
		return pwd;
	}
	

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

	

	
	

	public Privilege[] getDb_privileges() {
		return db_privileges;
	}
	

	public void setDb_privileges(Privilege[] db_privileges) {
		this.db_privileges = db_privileges;
	}
	

	@Override
	public String toString() {
		return "MgmUserBody [name=" + name + ", ip=" + ip + ", authType=" + authType + ", password=" + pwd
				+ ", db_privileges=" + Arrays.toString(db_privileges) + "]";
	}

	public static class Privilege {
		@JSONField(name = "db_name")
		private String dbName;

		@JSONField(name = "privileges")
		private String[] privileges;

		public String getDbName() {
			return dbName;
		}

		public void setDbName(String dbName) {
			this.dbName = dbName;
		}

		public String[] getPrivileges() {
			return privileges;
		}

		public void setPrivileges(String[] privileges) {
			this.privileges = privileges;
		}

		@Override
		public String toString() {
			return "Privilege [dbName=" + dbName + ", privileges=" + Arrays.toString(privileges) + "]";
		}

	}

}
