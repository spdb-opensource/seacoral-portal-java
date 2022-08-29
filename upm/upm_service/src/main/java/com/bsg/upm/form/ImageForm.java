package com.bsg.upm.form;

import java.io.Serializable;

import com.bsg.upm.query.VersionParam;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */
public class ImageForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 版本
     */
    private VersionParam version;
    
    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 描述
     */
    private String description;
    
    /**
     * 站点ID
     */
    private String site_id;
    
    /**
     * 镜像架构amd64\arm64
     */
    private String architecture;

    /**
     * @return the 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param 类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the 版本
     */
    public VersionParam getVersion() {
        return version;
    }

    /**
     * @param 版本
     *            the version to set
     */
    public void setVersion(VersionParam version) {
        this.version = version;
    }
    
    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
     * @return the 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param 描述
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	@Override
	public String toString() {
		return "ImageForm [type=" + type + ", version=" + version + ", enabled=" + enabled + ", description="
				+ description + ", site_id=" + site_id + ", architecture=" + architecture + "]";
	}

}
