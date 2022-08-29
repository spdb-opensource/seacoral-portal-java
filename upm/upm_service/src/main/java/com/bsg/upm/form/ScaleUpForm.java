package com.bsg.upm.form;

import java.io.Serializable;

public class ScaleUpForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * CPU数量
     */
    private Integer cpuCnt;

    /**
     * 内存容量
     */
    private Long memSize;

    /**
     * 数据目录大小
     */
    private Long dataDirSize;

    /**
     * 日志目录大小
     */
    private Long logDirSize;

    /**
     * 带宽
     */
    private Integer networkBandwidth;

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
     * 获取CPU数量
     * 
     * @return cpuCnt CPU数量
     */
    public Integer getCpuCnt() {
        return cpuCnt;
    }

    /**
     * 设置CPU数量
     * 
     * @param cpuCnt
     *            CPU数量
     */
    public void setCpuCnt(Integer cpuCnt) {
        this.cpuCnt = cpuCnt;
    }

    /**
     * 获取内存容量
     * 
     * @return memSize 内存容量
     */
    public Long getMemSize() {
        return memSize;
    }

    /**
     * 设置内存容量
     * 
     * @param memSize
     *            内存容量
     */
    public void setMemSize(Long memSize) {
        this.memSize = memSize;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ScaleUpForm [type=" + type + ", cpuCnt=" + cpuCnt + ", memSize=" + memSize + ", dataDirSize="
                + dataDirSize + ", logDirSize=" + logDirSize + ", networkBandwidth=" + networkBandwidth + "]";
    }

}
