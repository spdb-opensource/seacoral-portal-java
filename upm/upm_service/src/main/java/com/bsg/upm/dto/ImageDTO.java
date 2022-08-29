package com.bsg.upm.dto;


import java.io.Serializable;


/**
 * @author ZhuXH
 * @date 2019年7月8日
 */

public class ImageDTO extends BaseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 版本
     */
    private VersionDTO version;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 描述
     */
    private String description;

    /**
     * 任务
     */
    private TaskDTO task;

    /**
     * 创建
     */
    private InfoDTO created;

    /**
     * 变更
     */
    private InfoDTO modified;

    /**
     * 硬件架构
     */
    private String architecture;
    
    /**
     * 变更
     */
    private SiteDTO site;
    
    /**
     * 是否停用
     */
    private Boolean unschedulable;

    public Boolean getUnschedulable() {
		return unschedulable;
	}

	public void setUnschedulable(Boolean unschedulable) {
		this.unschedulable = unschedulable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VersionDTO getVersion() {
		return version;
	}

	public void setVersion(VersionDTO version) {
		this.version = version;
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

	public TaskDTO getTask() {
		return task;
	}

	public void setTask(TaskDTO task) {
		this.task = task;
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

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public SiteDTO getSite() {
		return site;
	}

	public void setSite(SiteDTO site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "ImageDTO [id=" + id + ", type=" + type + ", version=" + version + ", enabled=" + enabled
				+ ", description=" + description + ", task=" + task + ", created=" + created + ", modified=" + modified
				+ ", architecture=" + architecture + ", site=" + site + ", unschedulable=" + unschedulable + "]";
	}

}
