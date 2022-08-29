package com.bsg.upm.form;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月6日
 */
public class MaxUsage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * CPU最大使用率
     */
    private Integer cpu;

    /**
     * 内存最大使用率
     */
    private Integer mem;

    /**
     * 带宽最大使用率
     */
    private Integer bandwidth;

    /**
     * 存储最大使用率
     */
    private Integer storage;

    /**
     * @return the CPU最大使用率
     */
    public Integer getCpu() {
        return cpu;
    }

    /**
     * @param CPU最大使用率
     *            the cpu to set
     */
    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the 内存最大使用率
     */
    public Integer getMem() {
        return mem;
    }

    /**
     * @param 内存最大使用率
     *            the mem to set
     */
    public void setMem(Integer mem) {
        this.mem = mem;
    }

    /**
     * @return the 带宽最大使用率
     */
    public Integer getBandwidth() {
        return bandwidth;
    }

    /**
     * @param 带宽最大使用率
     *            the bandwidth to set
     */
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * @return the 存储最大使用率
     */
    public Integer getStorage() {
        return storage;
    }

    /**
     * @param 存储最大使用率
     *            the storage to set
     */
    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MaxUage [cpu=" + cpu + ", mem=" + mem + ", bandwidth=" + bandwidth + ", storage=" + storage + "]";
    }

}
