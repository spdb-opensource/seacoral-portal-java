package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月9日
 */
public class MgmHostQuery implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主机名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 主机ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 所属站点ID
     */
    @JSONField(name = "site_id")
    private String siteId;

    /**
     * 所属集群ID
     */
    @JSONField(name = "cluster_id")
    private String clusterId;
    
    /**
     * 是否启用
     */
    @JSONField(name = "enabled")
    private Boolean enabled;

    public Boolean getEnabled() {
		return enabled;
	}
	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	/**
     * @return the 主机名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param 主机名称
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the 主机ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param 主机ID
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the 所属站点ID
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param 所属站点ID
     *            the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the 所属集群ID
     */
    public String getClusterId() {
        return clusterId;
    }

    /**
     * @param 所属集群ID
     *            the clusterId to set
     */
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		return "MgmHostQuery [name=" + name + ", id=" + id + ", siteId=" + siteId + ", clusterId=" + clusterId
				+ ", enabled=" + enabled + "]";
	}

}
