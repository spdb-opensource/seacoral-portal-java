package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月9日
 */
public class MgmClusterQuery implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 集群名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 集群ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 站点ID
     */
    @JSONField(name = "site_id")
    private String siteId;

    /**
     * 是否启用
     */
    @JSONField(name = "enabled")
    private Boolean enabled;

    /**
     * 区域代码
     */
    @JSONField(name = "zone")
    private String zone;

    /**
     * @return the 集群名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param 集群名称
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the 集群ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param 集群ID
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the 站点ID
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param 站点ID
     *            the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the 是否启用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param 是否启用
     *            the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the 区域代码
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param 区域代码
     *            the zone to set
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmClusterQuery [name=" + name + ", id=" + id + ", siteId=" + siteId + ", enabled=" + enabled
                + ", zone=" + zone + "]";
    }

}
