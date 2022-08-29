package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.mgm.model.MgmModel.Info;

public class MgmRemoteStoragePool extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "storage")
	private Storage storage;

	@JSONField(name = "native_id")
	private String nativeId;

	@JSONField(name = "performance")
	private String performance;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "capacity")
	private Integer capacity;

	@JSONField(name = "used")
	private Integer used;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "task")
	private Task task;

	@JSONField(name = "created")
	private Info created;

	@JSONField(name = "modified")
	private Info modified;

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

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Info getCreated() {
		return created;
	}

	public void setCreated(Info created) {
		this.created = created;
	}

	public Info getModified() {
		return modified;
	}

	public void setModified(Info modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "MgmRemoteStoragePool [id=" + id + ", name=" + name + ", storage=" + storage + ", nativeId=" + nativeId
				+ ", performance=" + performance + ", type=" + type + ", capacity=" + capacity + ", used=" + used
				+ ", enabled=" + enabled + ", desc=" + desc + ", task=" + task + ", created=" + created + ", modified="
				+ modified + "]";
	}

	public static class Storage {
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
			return "Storage [id=" + id + ", name=" + name + "]";
		}

	}

}
