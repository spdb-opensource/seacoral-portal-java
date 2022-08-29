package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 规模实体类
 * @author ZhuXH
 * @date 2019年7月3日
 */
public class ScaleDO implements Serializable {

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
    private String type;

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
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 修改者
     */
    private String editor;

    /**
     * @return the 
     */
    public String getId() {
        return id;
    }
    

    /**
     * @param  the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    

    /**
     * @return the 类型
     */
    public String getType() {
        return type;
    }
    

    /**
     * @param 类型 the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    

    /**
     * @return the 名称
     */
    public String getName() {
        return name;
    }
    

    /**
     * @param 名称 the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    

    /**
     * @return the CPU数量
     */
    public Integer getCpuCnt() {
        return cpuCnt;
    }
    

    /**
     * @param CPU数量 the cpuCnt to set
     */
    public void setCpuCnt(Integer cpuCnt) {
        this.cpuCnt = cpuCnt;
    }
    

    /**
     * @return the 内存容量
     */
    public Long getMemSize() {
        return memSize;
    }
    

    /**
     * @param 内存容量 the memSize to set
     */
    public void setMemSize(Long memSize) {
        this.memSize = memSize;
    }
    

    /**
     * @return the 是否可用
     */
    public Boolean getEnabled() {
        return enabled;
    }
    

    /**
     * @param 是否可用 the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    

    /**
     * @return the 显示顺序
     */
    public Integer getSequence() {
        return sequence;
    }
    

    /**
     * @param 显示顺序 the sequence to set
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    

    /**
     * @return the 描述
     */
    public String getDescription() {
        return description;
    }
    

    /**
     * @param 描述 the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    

    /**
     * @return the 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    

    /**
     * @param 创建时间 the gmtCreate to set
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    

    /**
     * @return the 创建者
     */
    public String getCreator() {
        return creator;
    }
    

    /**
     * @param 创建者 the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    

    /**
     * @return the 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }
    

    /**
     * @param 修改时间 the gmtModified to set
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
    

    /**
     * @return the 修改者
     */
    public String getEditor() {
        return editor;
    }
    

    /**
     * @param 修改者 the editor to set
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ScaleDO [id=" + id + ", type=" + type + ", name=" + name + ", cpuCnt=" + cpuCnt + ", memSize=" + memSize
                + ", enabled=" + enabled + ", sequence=" + sequence + ", description=" + description + ", gmtCreate="
                + gmtCreate + ", creator=" + creator + ", gmtModified=" + gmtModified + ", editor=" + editor + "]";
    }
    

}
