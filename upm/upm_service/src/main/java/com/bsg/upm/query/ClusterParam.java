package com.bsg.upm.query;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月4日
 */
public class ClusterParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private String siteId;

    /**
     * 可用状态
     */
    private Boolean enabled;

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
     * 获取 可用状态
     * 
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }
    

    /**
     * 设置可用状态
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClusterParam [siteId=" + siteId + ", enabled=" + enabled + "]";
    }
    

}
