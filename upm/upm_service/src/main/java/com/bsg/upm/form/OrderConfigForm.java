package com.bsg.upm.form;

import java.io.Serializable;

public class OrderConfigForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 架构编码
     */
    private Long archId;

    /**
     * 规模编码
     */
    private Long scaleId;

    /**
     * 数据目录大小
     */
    private Long dataDirSize;

    /**
     * 数据目录类型
     */
    private String dataDirType;

    /**
     * 日志目录大小
     */
    private Long logDirSize;

    /**
     * 日志目录类型
     */
    private String logDirType;

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
     * 获取架构编码
     * 
     * @return archId 架构编码
     */
    public Long getArchId() {
        return archId;
    }

    /**
     * 设置架构编码
     * 
     * @param archId
     *            架构编码
     */
    public void setArchId(Long archId) {
        this.archId = archId;
    }

    /**
     * 获取规模编码
     * 
     * @return scaleId 规模编码
     */
    public Long getScaleId() {
        return scaleId;
    }

    /**
     * 设置规模编码
     * 
     * @param scaleId
     *            规模编码
     */
    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
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
        return "OrderConfigForm [type=" + type + ", archId=" + archId + ", scaleId=" + scaleId + ", dataDirSize="
                + dataDirSize + ", dataDirType=" + dataDirType + ", logDirSize=" + logDirSize + ", logDirType="
                + logDirType + ", networkBandwidth=" + networkBandwidth + ", networkHA=" + networkHA + ", nodeHA="
                + nodeHA + ", storageHA=" + storageHA + "]";
    }

}
