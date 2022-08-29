package com.bsg.upm.dto;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月3日
 */
public class GroupDTO extends BaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 唯一编码
     */
    private String id;

    /**
     * 名称
     */
    private String name;

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
     * 获取 唯一编码
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编码
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
        return "GroupDTO [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created
                + ", modified=" + modified + "]";
    }

}
