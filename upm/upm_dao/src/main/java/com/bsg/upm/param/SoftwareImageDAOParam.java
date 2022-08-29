package com.bsg.upm.param;

import java.io.Serializable;

public class SoftwareImageDAOParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 状态
     */
    private String status;

    /**
     * 类型
     */
    private String type;

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
     * 获取状态
     * 
     * @return status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     * 
     * @param status
     *            状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取类型
     * 
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type
     *            类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SoftwareImageDAOParam [siteId=" + siteId + ", status=" + status + ", type=" + type + "]";
    }

}
