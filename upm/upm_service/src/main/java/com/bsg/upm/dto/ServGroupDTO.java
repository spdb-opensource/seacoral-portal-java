package com.bsg.upm.dto;

import java.io.Serializable;

public class ServGroupDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 唯一编码
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
     * 区域编码
     */
    private Long areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 类型
     */
    private String type;

    /**
     * 所属者账号
     */
    private String ownerAccount;

    /**
     * 所属者姓名
     */
    private String ownerName;

    /**
     * 业务系统名称
     */
    private String businessSystemName;

    /**
     * 业务子系统名称
     */
    private String businessSubsystemName;

    /**
     * 名称
     */
    private String name;

    /**
     * 分片结构
     */
    private Boolean sharding;

    /**
     * 分片数量
     */
    private Integer shardingCnt;

    /**
     * 创建成功标志
     */
    private Boolean flag;

    /**
     * 是否发送告警
     */
    private Boolean sendAlarm;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 状态
     */
    private String status;

    /**
     * 任务编码
     */
    private Long taskId;

    /**
     * 任务动作类型
     */
    private String taskActionType;

    /**
     * 任务状态
     */
    private String taskStatus;

    /**
     * 获取唯一编码
     * 
     * @return id 唯一编码
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一编码
     * 
     * @param id
     *            唯一编码
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
     * 获取所属者账号
     * 
     * @return ownerAccount 所属者账号
     */
    public String getOwnerAccount() {
        return ownerAccount;
    }

    /**
     * 设置所属者账号
     * 
     * @param ownerAccount
     *            所属者账号
     */
    public void setOwnerAccount(String ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    /**
     * 获取所属者姓名
     * 
     * @return ownerName 所属者姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置所属者姓名
     * 
     * @param ownerName
     *            所属者姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
     * 获取名称
     * 
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *            名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分片结构
     * 
     * @return sharding 分片结构
     */
    public Boolean getSharding() {
        return sharding;
    }

    /**
     * 设置分片结构
     * 
     * @param sharding
     *            分片结构
     */
    public void setSharding(Boolean sharding) {
        this.sharding = sharding;
    }

    /**
     * 获取分片数量
     * 
     * @return shardingCnt 分片数量
     */
    public Integer getShardingCnt() {
        return shardingCnt;
    }

    /**
     * 设置分片数量
     * 
     * @param shardingCnt
     *            分片数量
     */
    public void setShardingCnt(Integer shardingCnt) {
        this.shardingCnt = shardingCnt;
    }

    /**
     * 获取创建成功标志
     * 
     * @return flag 创建成功标志
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置创建成功标志
     * 
     * @param flag
     *            创建成功标志
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 获取是否发送告警
     * 
     * @return sendAlarm 是否发送告警
     */
    public Boolean getSendAlarm() {
        return sendAlarm;
    }

    /**
     * 设置是否发送告警
     * 
     * @param sendAlarm
     *            是否发送告警
     */
    public void setSendAlarm(Boolean sendAlarm) {
        this.sendAlarm = sendAlarm;
    }

    /**
     * 获取创建时间
     * 
     * @return gmtCreate 创建时间
     */
    public String getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     * 
     * @param gmtCreate
     *            创建时间
     */
    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
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
     * 获取任务编码
     * 
     * @return taskId 任务编码
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置任务编码
     * 
     * @param taskId
     *            任务编码
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取任务动作类型
     * 
     * @return taskActionType 任务动作类型
     */
    public String getTaskActionType() {
        return taskActionType;
    }

    /**
     * 设置任务动作类型
     * 
     * @param taskActionType
     *            任务动作类型
     */
    public void setTaskActionType(String taskActionType) {
        this.taskActionType = taskActionType;
    }

    /**
     * 获取任务状态
     * 
     * @return taskStatus 任务状态
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态
     * 
     * @param taskStatus
     *            任务状态
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ServGroupDTO [id=" + id + ", siteId=" + siteId + ", siteName=" + siteName + ", areaId=" + areaId
                + ", areaName=" + areaName + ", type=" + type + ", ownerAccount=" + ownerAccount + ", ownerName="
                + ownerName + ", businessSystemName=" + businessSystemName + ", businessSubsystemName="
                + businessSubsystemName + ", name=" + name + ", sharding=" + sharding + ", shardingCnt=" + shardingCnt
                + ", flag=" + flag + ", sendAlarm=" + sendAlarm + ", gmtCreate=" + gmtCreate + ", status=" + status
                + ", taskId=" + taskId + ", taskActionType=" + taskActionType + ", taskStatus=" + taskStatus + "]";
    }

}
