package com.bsg.upm.dto;

import java.io.Serializable;

public class OrderConfigDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组类别
     */
    private String groupType;

    /**
     * 类型
     */
    private String type;

    /**
     * 架构
     */
    private Long archId;

    /**
     * 架构
     */
    private String archName;

    /**
     * 规模
     */
    private Long scaleId;

    /**
     * 规模
     */
    private String scaleName;

    /**
     * 数据目录大小
     */
    private Long dataDirSize;

    /**
     * 数据目录类型
     */
    private String dataDirType;

    /**
     * 数据目录类型
     */
    private String dataDirTypeDisplayName;

    /**
     * 日志目录大小
     */
    private Long logDirSize;

    /**
     * 日志目录类型
     */
    private String logDirType;

    /**
     * 日志目录类型
     */
    private String logDirTypeDisplayName;

    /**
     * 带宽
     */
    private Integer networkBandwidth;

    /**
     * 网络高可用
     */
    private Boolean networkHA;

    /**
     * 节点高可用
     */
    private Boolean nodeHA;

    /**
     * 存储高可用
     */
    private Boolean storageHA;

    /**
     * 获取组类别
     * 
     * @return groupType 组类别
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * 设置组类别
     * 
     * @param groupType
     *            组类别
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
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
     * 获取架构
     * 
     * @return archId 架构
     */
    public Long getArchId() {
        return archId;
    }

    /**
     * 设置架构
     * 
     * @param archId
     *            架构
     */
    public void setArchId(Long archId) {
        this.archId = archId;
    }

    /**
     * 获取架构
     * 
     * @return archName 架构
     */
    public String getArchName() {
        return archName;
    }

    /**
     * 设置架构
     * 
     * @param archName
     *            架构
     */
    public void setArchName(String archName) {
        this.archName = archName;
    }

    /**
     * 获取规模
     * 
     * @return scaleId 规模
     */
    public Long getScaleId() {
        return scaleId;
    }

    /**
     * 设置规模
     * 
     * @param scaleId
     *            规模
     */
    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    /**
     * 获取规模
     * 
     * @return scaleName 规模
     */
    public String getScaleName() {
        return scaleName;
    }

    /**
     * 设置规模
     * 
     * @param scaleName
     *            规模
     */
    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    /**
     * 获取数据目录大小
     * 
     * @return dataDirSize 数据目录大小
     */
    public Long getDataDirSize() {
        return dataDirSize;
    }

    /**
     * 设置数据目录大小
     * 
     * @param dataDirSize
     *            数据目录大小
     */
    public void setDataDirSize(Long dataDirSize) {
        this.dataDirSize = dataDirSize;
    }

    /**
     * 获取数据目录类型
     * 
     * @return dataDirType 数据目录类型
     */
    public String getDataDirType() {
        return dataDirType;
    }

    /**
     * 设置数据目录类型
     * 
     * @param dataDirType
     *            数据目录类型
     */
    public void setDataDirType(String dataDirType) {
        this.dataDirType = dataDirType;
    }

    /**
     * 获取数据目录类型
     * 
     * @return dataDirTypeDisplayName 数据目录类型
     */
    public String getDataDirTypeDisplayName() {
        return dataDirTypeDisplayName;
    }

    /**
     * 设置数据目录类型
     * 
     * @param dataDirTypeDisplayName
     *            数据目录类型
     */
    public void setDataDirTypeDisplayName(String dataDirTypeDisplayName) {
        this.dataDirTypeDisplayName = dataDirTypeDisplayName;
    }

    /**
     * 获取日志目录大小
     * 
     * @return logDirSize 日志目录大小
     */
    public Long getLogDirSize() {
        return logDirSize;
    }

    /**
     * 设置日志目录大小
     * 
     * @param logDirSize
     *            日志目录大小
     */
    public void setLogDirSize(Long logDirSize) {
        this.logDirSize = logDirSize;
    }

    /**
     * 获取日志目录类型
     * 
     * @return logDirType 日志目录类型
     */
    public String getLogDirType() {
        return logDirType;
    }

    /**
     * 设置日志目录类型
     * 
     * @param logDirType
     *            日志目录类型
     */
    public void setLogDirType(String logDirType) {
        this.logDirType = logDirType;
    }

    /**
     * 获取日志目录类型
     * 
     * @return logDirTypeDisplayName 日志目录类型
     */
    public String getLogDirTypeDisplayName() {
        return logDirTypeDisplayName;
    }

    /**
     * 设置日志目录类型
     * 
     * @param logDirTypeDisplayName
     *            日志目录类型
     */
    public void setLogDirTypeDisplayName(String logDirTypeDisplayName) {
        this.logDirTypeDisplayName = logDirTypeDisplayName;
    }

    /**
     * 获取带宽
     * 
     * @return networkBandwidth 带宽
     */
    public Integer getNetworkBandwidth() {
        return networkBandwidth;
    }

    /**
     * 设置带宽
     * 
     * @param networkBandwidth
     *            带宽
     */
    public void setNetworkBandwidth(Integer networkBandwidth) {
        this.networkBandwidth = networkBandwidth;
    }

    /**
     * 获取网络高可用
     * 
     * @return networkHA 网络高可用
     */
    public Boolean getNetworkHA() {
        return networkHA;
    }

    /**
     * 设置网络高可用
     * 
     * @param networkHA
     *            网络高可用
     */
    public void setNetworkHA(Boolean networkHA) {
        this.networkHA = networkHA;
    }

    /**
     * 获取节点高可用
     * 
     * @return nodeHA 节点高可用
     */
    public Boolean getNodeHA() {
        return nodeHA;
    }

    /**
     * 设置节点高可用
     * 
     * @param nodeHA
     *            节点高可用
     */
    public void setNodeHA(Boolean nodeHA) {
        this.nodeHA = nodeHA;
    }

    /**
     * 获取存储高可用
     * 
     * @return storageHA 存储高可用
     */
    public Boolean getStorageHA() {
        return storageHA;
    }

    /**
     * 设置存储高可用
     * 
     * @param storageHA
     *            存储高可用
     */
    public void setStorageHA(Boolean storageHA) {
        this.storageHA = storageHA;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OrderConfigDTO [groupType=" + groupType + ", type=" + type + ", archId=" + archId + ", archName="
                + archName + ", scaleId=" + scaleId + ", scaleName=" + scaleName + ", dataDirSize=" + dataDirSize
                + ", dataDirType=" + dataDirType + ", dataDirTypeDisplayName=" + dataDirTypeDisplayName
                + ", logDirSize=" + logDirSize + ", logDirType=" + logDirType + ", logDirTypeDisplayName="
                + logDirTypeDisplayName + ", networkBandwidth=" + networkBandwidth + ", networkHA=" + networkHA
                + ", nodeHA=" + nodeHA + ", storageHA=" + storageHA + "]";
    }

}
