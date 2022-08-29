package com.bsg.upm.query;

import java.io.Serializable;

public class OrderGroupParam implements Serializable {

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
     * 获取工单组类型
     * 
     * @return type 工单组类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置工单组类型
     * 
     * @param type
     *            工单组类型
     */
    public void setType(String type) {
        this.type = type;
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

    

    public String getActionType() {
		return actionType;
	}
	

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	

	/**
     * 获取所属者
     * 
     * @return owner 所属者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置所属者
     * 
     * @param owner
     *            所属者
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
        return "OrderGroupParam [siteId=" + siteId + ", type=" + type + ", status=" + status + ", actionType="
                + actionType + ", owner=" + owner + "]";
    }

}