package com.bsg.upm.param;

import java.io.Serializable;

public class BusinessSubsystemDAOParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务系统编码
     */
    private String businessSystemId;

    /**
     * 所属者
     */
    private String owner;

    /**
     * 获取业务系统编码
     * 
     * @return businessSystemId 业务系统编码
     */
    public String getBusinessSystemId() {
        return businessSystemId;
    }

    /**
     * 设置业务系统编码
     * 
     * @param businessSystemId
     *            业务系统编码
     */
    public void setBusinessSystemId(String businessSystemId) {
        this.businessSystemId = businessSystemId;
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
        return "BusinessSubsystemDAOParam [businessSystemId=" + businessSystemId + ", owner=" + owner + "]";
    }

}
