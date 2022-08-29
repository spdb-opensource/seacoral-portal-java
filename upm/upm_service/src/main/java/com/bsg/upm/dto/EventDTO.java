package com.bsg.upm.dto;

import java.io.Serializable;

public class EventDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 事件名称
     */
    private String name;

    /**
     * 等级
     */
    private String level;

    /**
     * 值
     */
    private String value;

    /**
     * 事件时间
     */
    private String eventTime;

    /**
     * 服务组名称
     */
    private String servGroupName;

    /**
     * 所属者
     */
    private String ownerUsername;

    /**
     * 所属者
     */
    private String ownerName;

    /**
     * 主机名称
     */
    private String hostName;

    /**
     * 主机IP
     */
    private String hostIp;

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 获取
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * 获取站点名称
     * 
     * @return siteName 站点名称
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 设置站点名称
     * 
     * @param siteName
     *            站点名称
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * 获取事件名称
     * 
     * @return name 事件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置事件名称
     * 
     * @param name
     *            事件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取等级
     * 
     * @return level 等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置等级
     * 
     * @param level
     *            等级
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取值
     * 
     * @return value 值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值
     * 
     * @param value
     *            值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取事件时间
     * 
     * @return eventTime 事件时间
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * 设置事件时间
     * 
     * @param eventTime
     *            事件时间
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
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
     * @return ownerUsername 所属者
     */
    public String getOwnerUsername() {
        return ownerUsername;
    }

    /**
     * 设置所属者
     * 
     * @param ownerUsername
     *            所属者
     */
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    /**
     * 获取所属者
     * 
     * @return ownerName 所属者
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置所属者
     * 
     * @param ownerName
     *            所属者
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
     * 获取集群名称
     * 
     * @return clusterName 集群名称
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * 设置集群名称
     * 
     * @param clusterName
     *            集群名称
     */
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EventDTO [id=" + id + ", siteId=" + siteId + ", siteName=" + siteName + ", name=" + name + ", level="
                + level + ", value=" + value + ", eventTime=" + eventTime + ", servGroupName=" + servGroupName
                + ", ownerUsername=" + ownerUsername + ", ownerName=" + ownerName + ", hostName=" + hostName
                + ", hostIp=" + hostIp + ", clusterName=" + clusterName + "]";
    }

}
