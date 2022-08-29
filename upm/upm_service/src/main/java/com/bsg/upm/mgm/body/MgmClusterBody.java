package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月4日
 */
public class MgmClusterBody implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "site_id")
    private String siteId;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "zone")
    private String zone;

    @JSONField(name = "image_type")
    private List<String> imageType;

    @JSONField(name = "ha_tag")
    private String haTag;

    @JSONField(name = "enabled")
    private Boolean enabled;


    @JSONField(name = "desc")
    private String desc;
    
    @JSONField(name = "created_user")
    private String createdUser;
    
    @JSONField(name = "modified_user")
    private String modifiedUser;
    
//    @JSONField(name = "nfs_ip")
//    private String nfs_ip;
//    
//    @JSONField(name = "nfs_source")
//    private String nfs_source;
//    
//    @JSONField(name = "nfs_opts")
//    private String nfs_opts;
    /**
     * 获取 siteId
     * 
     * @return siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * 设置siteId
     * 
     * @param siteId
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取 name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 zone
     * 
     * @return zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * 设置zone
     * 
     * @param zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * 获取 imageType
     * 
     * @return imageType
     */
    public List<String> getImageType() {
        return imageType;
    }

    /**
     * 设置imageType
     * 
     * @param imageType
     */
    public void setImageType(List<String> imageType) {
        this.imageType = imageType;
    }

    /**
     * 获取 haTag
     * 
     * @return haTag
     */
    public String getHaTag() {
        return haTag;
    }

    /**
     * 设置haTag
     * 
     * @param haTag
     */
    public void setHaTag(String haTag) {
        this.haTag = haTag;
    }

    /**
     * 获取 enabled
     * 
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置enabled
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 desc
     * 
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置desc
     * 
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取 createdUser
     * 
     * @return createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * 设置createdUser
     * 
     * @param createdUser
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * 获取 modifiedUser
     * 
     * @return modifiedUser
     */
    public String getModifiedUser() {
        return modifiedUser;
    }

    /**
     * 设置modifiedUser
     * 
     * @param modifiedUser
     */
    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

//    public String getNfs_ip() {
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
		return "MgmClusterBody [siteId=" + siteId + ", name=" + name + ", zone=" + zone + ", imageType=" + imageType
				+ ", haTag=" + haTag + ", enabled=" + enabled + ", desc=" + desc + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser  + "]";
	}

}
