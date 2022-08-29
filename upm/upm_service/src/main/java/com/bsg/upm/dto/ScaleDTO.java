package com.bsg.upm.dto;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月3日
 */
public class ScaleDTO extends BaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 类型
     */
    private TypeDTO type;

    /**
     * 名称
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
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 显示顺序
     */
    private Integer sequence;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建
     */
    private InfoDTO created;

    /**
     * 变更
     */
    private InfoDTO modified;

    /**
     * 获取
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 类型
     * 
     * @return type
     */
    public TypeDTO getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type
     */
    public void setType(TypeDTO type) {
        this.type = type;
    }

    /**
     * 获取 名称
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 CPU数量
     * 
     * @return cpuCnt
     */
    public Integer getCpuCnt() {
        return cpuCnt;
    }

    /**
     * 设置CPU数量
     * 
     * @param cpuCnt
     */
    public void setCpuCnt(Integer cpuCnt) {
        this.cpuCnt = cpuCnt;
    }

    /**
     * 获取 内存容量
     * 
     * @return memSize
     */
    public Long getMemSize() {
        return memSize;
    }

    /**
     * 设置内存容量
     * 
     * @param memSize
     */
    public void setMemSize(Long memSize) {
        this.memSize = memSize;
    }

    /**
     * 获取 是否可用
     * 
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 显示顺序
     * 
     * @return sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置显示顺序
     * 
     * @param sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取 描述
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取 创建
     * 
     * @return created
     */
    public InfoDTO getCreated() {
        return created;
    }

    /**
     * 设置创建
     * 
     * @param created
     */
    public void setCreated(InfoDTO created) {
        this.created = created;
    }

    /**
     * 获取 变更
     * 
     * @return modified
     */
    public InfoDTO getModified() {
        return modified;
    }

    /**
     * 设置变更
     * 
     * @param modified
     */
    public void setModified(InfoDTO modified) {
        this.modified = modified;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ScaleDTO [id=" + id + ", type=" + type + ", name=" + name + ", cpuCnt=" + cpuCnt + ", memSize="
                + memSize + ", enabled=" + enabled + ", sequence=" + sequence + ", description=" + description
                + ", created=" + created + ", modified=" + modified + "]";
    }


}
