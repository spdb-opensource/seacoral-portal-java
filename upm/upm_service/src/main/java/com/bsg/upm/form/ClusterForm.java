package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月4日
 */
public class ClusterForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private String siteId;

    /**
     * 区域代码
     */
    private String areaCode;

    /**
     * 集群名称
     */
    private String name;

    /**
     * 包含软件
     */
    private List<String> imageTypes;

    /**
     * 高可用标签
     */
    private String haTag;
    
    private Boolean enabled;

    /**
     * 描述
     */
    private String description;
    
//    /**
//     * nfs服务IP地址
//     */
//    private String nfs_ip;
//    
//    /**
//     * nfs服务源目录
//     */
//    private String nfs_source;
//    
//    /**
//     * nfs服务挂载参数
//     */
//    private String nfs_opts;

    /**
     * 获取 站点编码
     * 
     * @return siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取 区域代码
     * 
     * @return areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区域代码
     * 
     * @param areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取 集群名称
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置集群名称
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 包含软件
     * 
     * @return imageTypes
     */
    public List<String> getImageTypes() {
        return imageTypes;
    }

    /**
     * 设置包含软件
     * 
     * @param imageTypes
     */
    public void setImageTypes(List<String> imageTypes) {
        this.imageTypes = imageTypes;
    }

    /**
     * 获取 高可用标签
     * 
     * @return haTag
     */
    public String getHaTag() {
        return haTag;
    }

    /**
     * 设置高可用标签
     * 
     * @param haTag
     */
    public void setHaTag(String haTag) {
        this.haTag = haTag;
    }

    
    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
     * 获取 描述
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

//	public String getNfs_ip() {
//		return nfs_ip;
//	}
//
//	public void setNfs_ip(String nfs_ip) {
//		this.nfs_ip = nfs_ip;
//	}
//
//	public String getNfs_source() {
//		return nfs_source;
//	}
//
//	public void setNfs_source(String nfs_source) {
//		this.nfs_source = nfs_source;
//	}
//
//	public String getNfs_opts() {
//		return nfs_opts;
//	}
//
//	public void setNfs_opts(String nfs_opts) {
//		this.nfs_opts = nfs_opts;
//	}

	@Override
	public String toString() {
		return "ClusterForm [siteId=" + siteId + ", areaCode=" + areaCode + ", name=" + name + ", imageTypes="
				+ imageTypes + ", haTag=" + haTag + ", enabled=" + enabled + ", description=" + description + "]";
	}

}
