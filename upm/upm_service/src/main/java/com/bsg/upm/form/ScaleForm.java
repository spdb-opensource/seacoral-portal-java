package com.bsg.upm.form;

import java.io.Serializable;

public class ScaleForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 架构名称
     */
    private String name;

    /**
     * CPU数量
     */
    private Integer cpuCnt;

    /**
     * 内存容量
     */
    private Long memSize;

    /**
     * 显示顺序
     */
    private Integer sequence;

    /**
     * 描述
     */
    private String description;

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
     * 获取架构名称
     * 
     * @return name 架构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置架构名称
     * 
     * @param name
     *            架构名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取显示顺序
     * 
     * @return sequence 显示顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置显示顺序
     * 
     * @param sequence
     *            显示顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取描述
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ScaleForm [type=" + type + ", name=" + name + ", cpuCnt=" + cpuCnt + ", memSize=" + memSize
                + ", sequence=" + sequence + ", description=" + description + "]";
    }

}