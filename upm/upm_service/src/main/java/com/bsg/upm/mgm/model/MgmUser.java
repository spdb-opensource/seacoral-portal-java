package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmUser extends MgmModel implements Serializable {

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

	@JSONField(name = "db_privileges")
	private Privilege[] privileges;

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

	public Privilege[] getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Privilege[] privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "MgmUser [name=" + name + ", ip=" + ip + ", authType=" + authType + ", privileges="
				+ Arrays.toString(privileges) + "]";
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
			return "DBPrivilege [dbName=" + dbName + ", privileges=" + Arrays.toString(privileges) + "]";
		}

	}

}
