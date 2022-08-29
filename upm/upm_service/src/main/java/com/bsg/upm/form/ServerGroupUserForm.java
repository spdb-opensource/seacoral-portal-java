package com.bsg.upm.form;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class ServerGroupUserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String whiteIps;
	private PrivilegeForm[] db_privileges;

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

	public String getWhiteIps() {
		return whiteIps;
	}

	public void setWhiteIps(String whiteIps) {
		this.whiteIps = whiteIps;
	}

	

	
	

	public PrivilegeForm[] getDb_privileges() {
		return db_privileges;
	}
	

	public void setDb_privileges(PrivilegeForm[] db_privileges) {
		this.db_privileges = db_privileges;
	}
	

	@Override
	public String toString() {
		return "ServerGroupUserForm [username=" + username + ", password=" + password + ", whiteIps=" + whiteIps
				+ ", db_privileges=" + Arrays.toString(db_privileges) + "]";
	}

	public static class PrivilegeForm implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
			return "PrivilegeForm [dbName=" + dbName + ", privileges=" + Arrays.toString(privileges) + "]";
		}

	}
}
