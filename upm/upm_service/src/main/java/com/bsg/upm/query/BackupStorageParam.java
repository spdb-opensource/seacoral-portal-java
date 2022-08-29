package com.bsg.upm.query;

import java.io.Serializable;

public class BackupStorageParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 可用状态
     */
    private Boolean enabled;

    /**
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取可用状态
     * 
     * @return enabled 可用状态
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置可用状态
     * 
     * @param enabled
     *            可用状态
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
        return "BackupStorageParam [siteId=" + siteId + ", enabled=" + enabled + "]";
    }

}