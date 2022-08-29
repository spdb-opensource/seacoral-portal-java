package com.bsg.upm.mgm.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmBackupStorage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "total_space")
    private Long totalSpace;

    @JSONField(name = "free_space")
    private Long freeSpace;

    /**
     * 获取totalSpace
     * 
     * @return totalSpace totalSpace
     */
    public Long getTotalSpace() {
        return totalSpace;
    }

    /**
     * 设置totalSpace
     * 
     * @param totalSpace
     *            totalSpace
     */
    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    /**
     * 获取freeSpace
     * 
     * @return freeSpace freeSpace
     */
    public Long getFreeSpace() {
        return freeSpace;
    }

    /**
     * 设置freeSpace
     * 
     * @param freeSpace
     *            freeSpace
     */
    public void setFreeSpace(Long freeSpace) {
        this.freeSpace = freeSpace;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmBackupStorage [totalSpace=" + totalSpace + ", freeSpace=" + freeSpace + "]";
    }

}
