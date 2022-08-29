package com.bsg.upm.param;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderGroupDAOParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 工单组类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建类型
     */
    private String actionType;

    /**
     * 所属者
     */
    private String owner;

    /**
     * @return the 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * @return the 工单组类型
     */
    public String getType() {
        return type;
    }

    /**
     * @return the 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the 创建类型
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @return the 所属者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param 站点编码
     *            the siteId to set
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * @param 工单组类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param 状态
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param 创建类型
     *            the createType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @param 所属者
     *            the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OrderGroupDAOParam [siteId=" + siteId + ", type=" + type + ", status=" + status + ", actionType="
                + actionType + ", owner=" + owner + "]";
    }

}
