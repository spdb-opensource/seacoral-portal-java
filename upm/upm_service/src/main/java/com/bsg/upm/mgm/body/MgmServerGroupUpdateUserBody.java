package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.form.ServerGroupUserForm.PrivilegeForm;

public class MgmServerGroupUpdateUserBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "ip")
	private String ip;

	@JSONField(name = "db_privileges")
	private com.bsg.upm.form.ServerGroupUpdateUserForm.PrivilegeForm[] db_privileges;

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


	
	
	

	public com.bsg.upm.form.ServerGroupUpdateUserForm.PrivilegeForm[] getDb_privileges() {
		return db_privileges;
	}
	

	public void setDb_privileges(com.bsg.upm.form.ServerGroupUpdateUserForm.PrivilegeForm[] privilegeForms) {
		this.db_privileges = privilegeForms;
	}
	

	@Override
	public String toString() {
		return "MgmServerGroupUpdateUserBody [name=" + name + ", ip=" + ip + ", db_privileges="
				+ Arrays.toString(db_privileges) + "]";
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
