package com.bsg.upm.form;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sequence;
    
    /**
     * 描述
     */
    private String description;

    /**
     * 获取角色名
     * 
     * @return name 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     * 
     * @param name
     *            角色名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 顺序
     * 
     * @return sequence
     */
    public Integer getSequence() {
        return sequence;
    }
    

    /**
     * 设置顺序
     * 
     * @param sequence
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleForm [name=" + name + ", sequence=" + sequence + ", description=" + description + "]";
    }

}
