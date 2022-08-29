package com.bsg.upm.dto;

import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Arrays;

/**
 * 服务组用户传输对象
 * 
 * @author swn
 *
 */
public class ServerGroupUserDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String whiteIps;
	private Privilege[] privileges;
	private String auth_type;

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

	public String getAuth_type() {
		return auth_type;
	}
	

	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}
	

	public Privilege[] getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Privilege[] privileges) {
		this.privileges = privileges;
	}

	

	@Override
	public String toString() {
		return "ServerGroupUserDTO [username=" + username + ", whiteIps=" + whiteIps + ", privileges="
				+ Arrays.toString(privileges) + ", auth_type=" + auth_type + "]";
	}



	public class Privilege {
		private String dbName;
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
