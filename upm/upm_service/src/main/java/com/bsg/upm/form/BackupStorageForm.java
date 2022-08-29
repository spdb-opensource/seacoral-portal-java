package com.bsg.upm.form;

import java.io.Serializable;

public class BackupStorageForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 备份存储名称
     */
    private String name;

    /**
     * 备份存储类型
     */
    private String type;

    /**
     * 备份存储地址
     */
    private String ip;

    /**
     * 备份存储目录
     */
    private String dir;

    /**
     * 备份存储挂载目录
     */
    private String mountDir;

    /**
     * 描述
     */
    private String description;

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
     * 获取备份存储名称
     * 
     * @return name 备份存储名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置备份存储名称
     * 
     * @param name
     *            备份存储名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取备份存储类型
     * 
     * @return type 备份存储类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置备份存储类型
     * 
     * @param type
     *            备份存储类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取备份存储地址
     * 
     * @return ip 备份存储地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置备份存储地址
     * 
     * @param ip
     *            备份存储地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取备份存储目录
     * 
     * @return dir 备份存储目录
     */
    public String getDir() {
        return dir;
    }

    /**
     * 设置备份存储目录
     * 
     * @param dir
     *            备份存储目录
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * 获取备份存储挂载目录
     * 
     * @return mountDir 备份存储挂载目录
     */
    public String getMountDir() {
        return mountDir;
    }

    /**
     * 设置备份存储挂载目录
     * 
     * @param mountDir
     *            备份存储挂载目录
     */
    public void setMountDir(String mountDir) {
        this.mountDir = mountDir;
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
        return "BackupStorageForm [siteId=" + siteId + ", name=" + name + ", type=" + type + ", ip=" + ip + ", dir="
                + dir + ", mountDir=" + mountDir + ", description=" + description + "]";
    }

}
