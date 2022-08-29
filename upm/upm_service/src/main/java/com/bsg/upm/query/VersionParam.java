package com.bsg.upm.query;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */
public class VersionParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 主版本
     */
    private Integer major;

    /**
     * 次版本
     */
    private Integer minor;

    /**
     * 修订版本
     */
    private Integer patch;

    /**
     * 编译版本
     */
    private Integer build;

    /**
     * @return the 主版本
     */
    public Integer getMajor() {
        return major;
    }

    /**
     * @param 主版本
     *            the major to set
     */
    public void setMajor(Integer major) {
        this.major = major;
    }

    /**
     * @return the 次版本
     */
    public Integer getMinor() {
        return minor;
    }

    /**
     * @param 次版本
     *            the minor to set
     */
    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    /**
     * @return the 修订版本
     */
    public Integer getPatch() {
        return patch;
    }

    /**
     * @param 修订版本
     *            the patch to set
     */
    public void setPatch(Integer patch) {
        this.patch = patch;
    }

    /**
     * @return the 编译版本
     */
    public Integer getBuild() {
        return build;
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
        return "Version [major=" + major + ", minor=" + minor + ", patch=" + patch + ", build=" + build + "]";
    }

}
