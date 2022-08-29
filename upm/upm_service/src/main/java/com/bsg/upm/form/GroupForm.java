package com.bsg.upm.form;

import java.io.Serializable;

public class GroupForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 获取组名
     * 
     * @return name 组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     * 
     * @param name
     *            组名
     */
    public void setName(String name) {
        this.name = name;
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
        return "GroupForm [name=" + name + ", description=" + description + "]";
    }

}
