package com.bsg.upm.dto;

import java.io.Serializable;

public class ArchDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;

    /**
     * 服务
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 
     */
    private String shardingType;

    /**
     * 主节点数量
     */
    private Integer masterNodeCnt;

    /**
     * 备节点数量
     */
    private Integer standbyNodeCnt;

    /**
     * 从节点数量
     */
    private Integer slaveNodeCnt;

    /**
     * 显示顺序
     */
    private Integer sequence;

    /**
     * 描述
     */
    private String description;

    /**
     * 获取
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取服务
     * 
     * @return type 服务
     */
    public String getType() {
        return type;
    }

    /**
     * 设置服务
     * 
     * @param type
     *            服务
     */
    public void setType(String type) {
        this.type = type;
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
     * 获取
     * 
     * @return shardingType
     */
    public String getShardingType() {
        return shardingType;
    }

    /**
     * 设置
     * 
     * @param shardingType
     */
    public void setShardingType(String shardingType) {
        this.shardingType = shardingType;
    }

    /**
     * 获取主节点数量
     * 
     * @return masterNodeCnt 主节点数量
     */
    public Integer getMasterNodeCnt() {
        return masterNodeCnt;
    }

    /**
     * 设置主节点数量
     * 
     * @param masterNodeCnt
     *            主节点数量
     */
    public void setMasterNodeCnt(Integer masterNodeCnt) {
        this.masterNodeCnt = masterNodeCnt;
    }

    /**
     * 获取备节点数量
     * 
     * @return standbyNodeCnt 备节点数量
     */
    public Integer getStandbyNodeCnt() {
        return standbyNodeCnt;
    }

    /**
     * 设置备节点数量
     * 
     * @param standbyNodeCnt
     *            备节点数量
     */
    public void setStandbyNodeCnt(Integer standbyNodeCnt) {
        this.standbyNodeCnt = standbyNodeCnt;
    }

    /**
     * 获取从节点数量
     * 
     * @return slaveNodeCnt 从节点数量
     */
    public Integer getSlaveNodeCnt() {
        return slaveNodeCnt;
    }

    /**
     * 设置从节点数量
     * 
     * @param slaveNodeCnt
     *            从节点数量
     */
    public void setSlaveNodeCnt(Integer slaveNodeCnt) {
        this.slaveNodeCnt = slaveNodeCnt;
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
        return "ArchDTO [id=" + id + ", type=" + type + ", name=" + name + ", shardingType=" + shardingType
                + ", masterNodeCnt=" + masterNodeCnt + ", standbyNodeCnt=" + standbyNodeCnt + ", slaveNodeCnt="
                + slaveNodeCnt + ", sequence=" + sequence + ", description=" + description + "]";
    }

}
