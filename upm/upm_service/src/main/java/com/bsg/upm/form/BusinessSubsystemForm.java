package com.bsg.upm.form;

import java.io.Serializable;

public class BusinessSubsystemForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务系统编码
     */
    private String businessSystemId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 获取业务系统编码
     * 
     * @return businessSystemId 业务系统编码
     */
    public String getBusinessSystemId() {
        return businessSystemId;
    }

    /**
     * 设置业务系统编码
     * 
     * @param businessSystemId
     *            业务系统编码
     */
    public void setBusinessSystemId(String businessSystemId) {
        this.businessSystemId = businessSystemId;
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
        return "BusinessSubsystemForm [businessSystemId=" + businessSystemId + ", name=" + name + ", description="
                + description + "]";
    }

}
