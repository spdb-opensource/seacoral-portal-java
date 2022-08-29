package com.bsg.upm.dto;

import java.io.Serializable;

public class ImageUnitDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单元名称
     */
    private String unitName;

    /**
     * 主机名称
     */
    private String hostName;

    /**
     * 主机IP
     */
    private String hostIp;

    /**
     * 服务组名称
     */
    private String servGroupName;

    /**
     * 所属者
     */
    private String owner;

    /**
     * 业务系统名称
     */
    private String businessSystemName;

    /**
     * 业务子系统名称
     */
    private String businessSubSystemName;

    /**
     * 获取单元名称
     * 
     * @return unitName 单元名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单元名称
     * 
     * @param unitName
     *            单元名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取主机名称
     * 
     * @return hostName 主机名称
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * 设置主机名称
     * 
     * @param hostName
     *            主机名称
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * 获取主机IP
     * 
     * @return hostIp 主机IP
     */
    public String getHostIp() {
        return hostIp;
    }

    /**
     * 设置主机IP
     * 
     * @param hostIp
     *            主机IP
     */
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    /**
     * 获取服务组名称
     * 
     * @return servGroupName 服务组名称
     */
    public String getServGroupName() {
        return servGroupName;
    }

    /**
     * 设置服务组名称
     * 
     * @param servGroupName
     *            服务组名称
     */
    public void setServGroupName(String servGroupName) {
        this.servGroupName = servGroupName;
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

    /**
     * 获取业务系统名称
     * 
     * @return businessSystemName 业务系统名称
     */
    public String getBusinessSystemName() {
        return businessSystemName;
    }

    /**
     * 设置业务系统名称
     * 
     * @param businessSystemName
     *            业务系统名称
     */
    public void setBusinessSystemName(String businessSystemName) {
        this.businessSystemName = businessSystemName;
    }

    /**
     * 获取业务子系统名称
     * 
     * @return businessSubSystemName 业务子系统名称
     */
    public String getBusinessSubSystemName() {
        return businessSubSystemName;
    }

    /**
     * 设置业务子系统名称
     * 
     * @param businessSubSystemName
     *            业务子系统名称
     */
    public void setBusinessSubSystemName(String businessSubSystemName) {
        this.businessSubSystemName = businessSubSystemName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ImageUnitDTO [unitName=" + unitName + ", hostName=" + hostName + ", hostIp=" + hostIp
                + ", servGroupName=" + servGroupName + ", owner=" + owner + ", businessSystemName=" + businessSystemName
                + ", businessSubSystemName=" + businessSubSystemName + "]";
    }

}
