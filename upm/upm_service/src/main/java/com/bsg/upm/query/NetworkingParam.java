package com.bsg.upm.query;

import java.io.Serializable;

public class NetworkingParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private String siteId;

    /**
     * 集群编码
     */
    private String clusterId;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取集群编码
     * 
     * @return clusterId 集群编码
     */
    public String getClusterId() {
        return clusterId;
    }

    /**
     * 设置集群编码
     * 
     * @param clusterId
     *            集群编码
     */
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * 获取是否启用
     * 
     * @return status 状态
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否启用
     * 
     * @param status
     *            状态
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NetworkingParam [siteId=" + siteId + ", clusterId=" + clusterId + ", enabled=" + enabled + "]";
    }

}
