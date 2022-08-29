package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */
public class MgmImageQuery implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 类型
     */
    @JSONField(name = "type")
    private String type;

    /**
     * 是否启用
     */
    @JSONField(name = "enabled")
    private String enabled;

    /**
     * 主版本
     */
    @JSONField(name = "major")
    private Integer major;
    /**
     * 次版本
     */
    @JSONField(name = "minor")
    private Integer minor;
    /**
     * 修订版本
     */
    @JSONField(name = "patch")
    private Integer patch;
    /**
     * 编译版本
     */
    @JSONField(name = "build")
    private Integer build;

    /**
     * @return the 编码
     */
    public String getId() {
        return id;
    }

    /**
     * @param 编码
     *            the id to set
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
     * @param 类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the 是否启用
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param 是否启用
     *            the enabled to set
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the 主版本
     */
    public Integer getMajor() {
        return major;
    }

    /**
     * @return the 次版本
     */
    public Integer getMinor() {
        return minor;
    }

    /**
     * @return the 修订版本
     */
    public Integer getPatch() {
        return patch;
    }

    /**
     * @return the 编译版本
     */
    public Integer getBuild() {
        return build;
    }

    /**
     * @param 主版本
     *            the major to set
     */
    public void setMajor(Integer major) {
        this.major = major;
    }

    /**
     * @param 次版本
     *            the minor to set
     */
    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    /**
     * @param 修订版本
     *            the patch to set
     */
    public void setPatch(Integer patch) {
        this.patch = patch;
    }

    /**
     * @param 编译版本
     *            the build to set
     */
    public void setBuild(Integer build) {
        this.build = build;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmImageQuery [id=" + id + ", type=" + type + ", enabled=" + enabled + ", major=" + major + ", minor="
                + minor + ", patch=" + patch + ", build=" + build + "]";
    }

}
