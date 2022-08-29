package com.bsg.upm.form;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class ServerGroupUpdateUserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String whiteIps;
	private PrivilegeForm[] db_privileges;
	private String ip;


	@Override
	public String toString() {
		return "ServerGroupUpdateUserForm [username=" + username + ", whiteIps=" + whiteIps + ", db_privileges="
				+ Arrays.toString(db_privileges) + ", ip=" + ip + "]";
	}



	public String getUsername() {
		return username;
	}
	


	public void setUsername(String username) {
		this.username = username;
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
	


	public String getIp() {
		return ip;
	}
	


	public void setIp(String ip) {
		this.ip = ip;
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
