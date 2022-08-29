package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

public class EventDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;

    /**
     * 关联编码
     */
    private String relateId;

    /**
     * 类型
     */
    private String type;

    /**
     * 监控项
     */
    private String metric;

    /**
     * 监控项描述
     */
    private String metricDescription;

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
    private Date relateCreate;

    /**
     * 主机关联编码
     */
    private String hostRelateId;

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
     * 区域编码
     */
    private Long areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 单元编码
     */
    private String unitRelateId;

    /**
     * 单元名称
     */
    private String unitRelateName;

    /**
     * 服务组名称
     */
    private String servGroupName;

    /**
     * 业务系统名称
     */
    private String businessSystemName;

    /**
     * 业务子系统名称
     */
    private String businessSubsystemName;

    /**
     * 所属者
     */
    private String ownerUsername;

    /**
     * 所属者
     */
    private String ownerName;

    /**
     * 是否告警
     */
    private Boolean alarm;

    /**
     * 是否已发送
     */
    private Boolean send;

    /**
     * 告警发送时间
     */
    private Date sendTime;

    /**
     * 创建时间
     */
    private Date gmtCreate;

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
     * 获取关联编码
     * 
     * @return relateId 关联编码
     */
    public String getRelateId() {
        return relateId;
    }

    /**
     * 设置关联编码
     * 
     * @param relateId
     *            关联编码
     */
    public void setRelateId(String relateId) {
        this.relateId = relateId;
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

    /**
     * 获取监控项
     * 
     * @return metric 监控项
     */
    public String getMetric() {
        return metric;
    }

    /**
     * 设置监控项
     * 
     * @param metric
     *            监控项
     */
    public void setMetric(String metric) {
        this.metric = metric;
    }

    /**
     * 获取监控项描述
     * 
     * @return metricDescription 监控项描述
     */
    public String getMetricDescription() {
        return metricDescription;
    }

    /**
     * 设置监控项描述
     * 
     * @param metricDescription
     *            监控项描述
     */
    public void setMetricDescription(String metricDescription) {
        this.metricDescription = metricDescription;
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
     * @return relateCreate 事件时间
     */
    public Date getRelateCreate() {
        return relateCreate;
    }

    /**
     * 设置事件时间
     * 
     * @param relateCreate
     *            事件时间
     */
    public void setRelateCreate(Date relateCreate) {
        this.relateCreate = relateCreate;
    }

    /**
     * 获取主机关联编码
     * 
     * @return hostRelateId 主机关联编码
     */
    public String getHostRelateId() {
        return hostRelateId;
    }

    /**
     * 设置主机关联编码
     * 
     * @param hostRelateId
     *            主机关联编码
     */
    public void setHostRelateId(String hostRelateId) {
        this.hostRelateId = hostRelateId;
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

    /**
     * 获取区域编码
     * 
     * @return areaId 区域编码
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 设置区域编码
     * 
     * @param areaId
     *            区域编码
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取区域名称
     * 
     * @return areaName 区域名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置区域名称
     * 
     * @param areaName
     *            区域名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
     * 获取单元编码
     * 
     * @return unitRelateId 单元编码
     */
    public String getUnitRelateId() {
        return unitRelateId;
    }

    /**
     * 设置单元编码
     * 
     * @param unitRelateId
     *            单元编码
     */
    public void setUnitRelateId(String unitRelateId) {
        this.unitRelateId = unitRelateId;
    }

    /**
     * 获取单元名称
     * 
     * @return unitRelateName 单元名称
     */
    public String getUnitRelateName() {
        return unitRelateName;
    }

    /**
     * 设置单元名称
     * 
     * @param unitRelateName
     *            单元名称
     */
    public void setUnitRelateName(String unitRelateName) {
        this.unitRelateName = unitRelateName;
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
     * @return businessSubsystemName 业务子系统名称
     */
    public String getBusinessSubsystemName() {
        return businessSubsystemName;
    }

    /**
     * 设置业务子系统名称
     * 
     * @param businessSubsystemName
     *            业务子系统名称
     */
    public void setBusinessSubsystemName(String businessSubsystemName) {
        this.businessSubsystemName = businessSubsystemName;
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
     * 获取是否告警
     * 
     * @return alarm 是否告警
     */
    public Boolean getAlarm() {
        return alarm;
    }

    /**
     * 设置是否告警
     * 
     * @param alarm
     *            是否告警
     */
    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    /**
     * 获取是否已发送
     * 
     * @return send 是否已发送
     */
    public Boolean getSend() {
        return send;
    }

    /**
     * 设置是否已发送
     * 
     * @param send
     *            是否已发送
     */
    public void setSend(Boolean send) {
        this.send = send;
    }

    /**
     * 获取告警发送时间
     * 
     * @return sendTime 告警发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置告警发送时间
     * 
     * @param sendTime
     *            告警发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取创建时间
     * 
     * @return gmtCreate 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     * 
     * @param gmtCreate
     *            创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EventDO [id=" + id + ", relateId=" + relateId + ", type=" + type + ", metric=" + metric
                + ", metricDescription=" + metricDescription + ", level=" + level + ", value=" + value
                + ", relateCreate=" + relateCreate + ", hostRelateId=" + hostRelateId + ", hostName=" + hostName
                + ", hostIp=" + hostIp + ", clusterName=" + clusterName + ", areaId=" + areaId + ", areaName="
                + areaName + ", siteId=" + siteId + ", siteName=" + siteName + ", unitRelateId=" + unitRelateId
                + ", unitRelateName=" + unitRelateName + ", servGroupName=" + servGroupName + ", businessSystemName="
                + businessSystemName + ", businessSubsystemName=" + businessSubsystemName + ", ownerUsername="
                + ownerUsername + ", ownerName=" + ownerName + ", alarm=" + alarm + ", send=" + send + ", sendTime="
                + sendTime + ", gmtCreate=" + gmtCreate + "]";
    }

}
