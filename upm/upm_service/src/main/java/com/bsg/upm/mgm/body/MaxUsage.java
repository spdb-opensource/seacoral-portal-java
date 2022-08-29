package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

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

    @JSONField(name = "cpu")
    private Integer cpu;

    @JSONField(name = "mem")
    private Integer mem;

    @JSONField(name = "storage_host")
    private Integer storageHost;

    @JSONField(name = "net_bandwidth")
    private Integer netBandwidth;

    /**
     * @return the cpu
     */
    public Integer getCpu() {
        return cpu;
    }

    /**
     * @param cpu
     *            the cpu to set
     */
    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the mem
     */
    public Integer getMem() {
        return mem;
    }

    /**
     * @param mem
     *            the mem to set
     */
    public void setMem(Integer mem) {
        this.mem = mem;
    }

    /**
     * @return the storageHost
     */
    public Integer getStorageHost() {
        return storageHost;
    }

    /**
     * @param storageHost
     *            the storageHost to set
     */
    public void setStorageHost(Integer storageHost) {
        this.storageHost = storageHost;
    }

    /**
     * @return the netBandwidth
     */
    public Integer getNetBandwidth() {
        return netBandwidth;
    }

    /**
     * @param netBandwidth
     *            the netBandwidth to set
     */
    public void setNetBandwidth(Integer netBandwidth) {
        this.netBandwidth = netBandwidth;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MaxUsage [cpu=" + cpu + ", mem=" + mem + ", storageHost=" + storageHost + ", netBandwidth="
                + netBandwidth + "]";
    }

}
