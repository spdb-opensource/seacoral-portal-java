package com.bsg.upm.dto;

import java.io.Serializable;

public class ScaleBaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private Double cpuCnt;
    private Double memSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCpuCnt() {
        return cpuCnt;
    }

    public void setCpuCnt(Double cpuCnt) {
        this.cpuCnt = cpuCnt;
    }

    public Double getMemSize() {
        return memSize;
    }

    public void setMemSize(Double memSize) {
        this.memSize = memSize;
    }

    @Override
    public String toString() {
        return "ScaleBaseDTO [name=" + name + ", cpuCnt=" + cpuCnt + ", memSize=" + memSize + "]";
    }

}
