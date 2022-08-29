package com.bsg.upm.dto;

import java.io.Serializable;

import com.bsg.upm.dto.BaseDTO.InfoDTO;

public class RemoteStoragePoolDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String code;
//	private TypeDTO type;
	private TypeDTO performance;
	private Long totalSize;
	private Long usedSize;
	private Boolean enabled;
	private String description;

	/**
	 * 创建
	 */
	private InfoDTO created;

	/**
	 * 变更
	 */
	private InfoDTO modified;

	private TaskDTO task;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public TypeDTO getType() {
//		return type;
//	}
//
//	public void setType(TypeDTO type) {
//		this.type = type;
//	}

	public TypeDTO getPerformance() {
		return performance;
	}

	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}
	

	public void setPerformance(TypeDTO performance) {
		this.performance = performance;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Long getUsedSize() {
		return usedSize;
	}

	public void setUsedSize(Long usedSize) {
		this.usedSize = usedSize;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InfoDTO getCreated() {
		return created;
	}

	public void setCreated(InfoDTO created) {
		this.created = created;
	}

	public InfoDTO getModified() {
		return modified;
	}

	public void setModified(InfoDTO modified) {
		this.modified = modified;
	}

	public TaskDTO getTask() {
		return task;
	}

	public void setTask(TaskDTO task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "RemoteStoragePoolDTO [id=" + id + ", code=" + code + ", performance=" + performance + ", totalSize="
				+ totalSize + ", usedSize=" + usedSize + ", enabled=" + enabled + ", description=" + description
				+ ", created=" + created + ", modified=" + modified + ", task=" + task + "]";
	}

	

}
