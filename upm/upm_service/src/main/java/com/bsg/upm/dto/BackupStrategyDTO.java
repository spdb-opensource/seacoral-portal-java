package com.bsg.upm.dto;

import java.io.Serializable;

public class BackupStrategyDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String storage;
	private String servGroupId;
	private String unitId;
	private String schedule;
	private Boolean enabled;
	private Integer retention;
	private String desc;
	private String role;
	private String backupType;
	private String tables;
	private OwnerDTO created;
	private OwnerDTO modified;
	
	

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
	

	public String getServGroupId() {
		return servGroupId;
	}
	

	public void setServGroupId(String servGroupId) {
		this.servGroupId = servGroupId;
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
	

	public String getBackupType() {
		return backupType;
	}
	

	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}
	

	public String getTables() {
		return tables;
	}
	

	public void setTables(String tables) {
		this.tables = tables;
	}
	



	public OwnerDTO getCreated() {
		return created;
	}
	


	public void setCreated(OwnerDTO created) {
		this.created = created;
	}
	


	

	public OwnerDTO getModified() {
		return modified;
	}
	


	public void setModified(OwnerDTO modified) {
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
		return "BackupStrategyDTO [id=" + id + ", name=" + name + ", storage=" + storage + ", servGroupId="
				+ servGroupId + ", unitId=" + unitId + ", schedule=" + schedule + ", enabled=" + enabled
				+ ", retention=" + retention + ", desc=" + desc + ", role=" + role + ", backupType=" + backupType
				+ ", tables=" + tables + ", created=" + created + ", modified=" + modified + "]";
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
	
	public class OwnerDTO {
		private String username;
		private String name;
		private String timestamp;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		

		public String getTimestamp() {
			return timestamp;
		}
		

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			return "OwnerDTO [username=" + username + ", name=" + name + ", timestamp=" + timestamp + "]";
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
}
