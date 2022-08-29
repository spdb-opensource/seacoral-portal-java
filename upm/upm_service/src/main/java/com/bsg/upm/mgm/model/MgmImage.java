package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.dto.BaseDTO;
import com.bsg.upm.mgm.model.MgmModel.Info;
import com.bsg.upm.mgm.model.MgmModel.Task;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */
public class MgmImage extends MgmModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "major")
    private Integer major;

    @JSONField(name = "minor")
    private Integer minor;

    @JSONField(name = "patch")
    private Integer patch;

    @JSONField(name = "build")
    private Integer build;

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
    
    @JSONField(name = "site")
    private Site site;
    
    @JSONField(name = "arch")
    private String arch;
    
    @JSONField(name = "unschedulable")
    private Boolean unschedulable;

    public Boolean getUnschedulable() {
		return unschedulable;
	}

	public void setUnschedulable(Boolean unschedulable) {
		this.unschedulable = unschedulable;
	}

    public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	/**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the major
     */
    public Integer getMajor() {
        return major;
    }

    /**
     * @param major
     *            the major to set
     */
    public void setMajor(Integer major) {
        this.major = major;
    }

    /**
     * @return the minor
     */
    public Integer getMinor() {
        return minor;
    }

    /**
     * @param minor
     *            the minor to set
     */
    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    /**
     * @return the patch
     */
    public Integer getPatch() {
        return patch;
    }

    /**
     * @param patch
     *            the patch to set
     */
    public void setPatch(Integer patch) {
        this.patch = patch;
    }

    /**
     * @return the build
     */
    public Integer getBuild() {
        return build;
    }

    /**
     * @param build
     *            the build to set
     */
    public void setBuild(Integer build) {
        this.build = build;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task
     *            the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * @return the created
     */
    public Info getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(Info created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public Info getModified() {
        return modified;
    }

    /**
     * @param modified
     *            the modified to set
     */
    public void setModified(Info modified) {
        this.modified = modified;
    }

    public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "MgmImage [id=" + id + ", type=" + type + ", major=" + major + ", minor=" + minor + ", patch=" + patch
				+ ", build=" + build + ", enabled=" + enabled + ", desc=" + desc + ", task=" + task + ", created="
				+ created + ", modified=" + modified + ", site=" + site + ", arch=" + arch + "]";
	}

}
