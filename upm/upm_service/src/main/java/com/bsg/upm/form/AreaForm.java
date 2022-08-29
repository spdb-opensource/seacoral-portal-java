package com.bsg.upm.form;

import java.io.Serializable;

public class AreaForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 备份存储编码
     */
    private Long backupStorageId;

    /**
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取区域名称
     * 
     * @return name 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区域名称
     * 
     * @param name
     *            区域名称
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

    /**
     * 获取备份存储编码
     * 
     * @return backupStorageId 备份存储编码
     */
    public Long getBackupStorageId() {
        return backupStorageId;
    }

    /**
     * 设置备份存储编码
     * 
     * @param backupStorageId
     *            备份存储编码
     */
    public void setBackupStorageId(Long backupStorageId) {
        this.backupStorageId = backupStorageId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AreaForm [siteId=" + siteId + ", name=" + name + ", description=" + description + ", backupStorageId="
                + backupStorageId + "]";
    }

}
