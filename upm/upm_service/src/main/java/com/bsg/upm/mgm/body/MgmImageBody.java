package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */
public class MgmImageBody implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

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

    @JSONField(name = "created_user")
    private String createdUser;

    @JSONField(name = "modified_user")
    private String modifiedUser;
    
    @JSONField(name = "site_id")
    private String site_id;
    
    @JSONField(name = "unschedulable")
    private Boolean unschedulable;

    @JSONField(name = "arch")
    private String arch;
    
    public Boolean getUnschedulable() {
		return unschedulable;
	}

	public void setUnschedulable(Boolean unschedulable) {
		this.unschedulable = unschedulable;
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
     * @return the createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser
     *            the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * @return the modifiedUser
     */
    public String getModifiedUser() {
        return modifiedUser;
    }

    /**
     * @param modifiedUser
     *            the modifiedUser to set
     */
    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }
    
    

    public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	@Override
	public String toString() {
		return "MgmImageBody [type=" + type + ", major=" + major + ", minor=" + minor + ", patch=" + patch + ", build="
				+ build + ", enabled=" + enabled + ", desc=" + desc + ", createdUser=" + createdUser + ", modifiedUser="
				+ modifiedUser + ", site_id=" + site_id + ", unschedulable=" + unschedulable + ", arch=" + arch + "]";
	}

}
