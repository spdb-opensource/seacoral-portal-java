package com.bsg.upm.form;

import java.io.Serializable;

public class ImageUpdateForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 主版本
     */
    private Integer majorVersion;

    /**
     * 次版本
     */
    private Integer minorVersion;

    /**
     * 修订版本
     */
    private Integer patchVersion;

    /**
     * 编译版本
     */
    private Integer buildVersion;

    /**
     * 获取类型
     * 
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type
     *            类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取主版本
     * 
     * @return majorVersion 主版本
     */
    public Integer getMajorVersion() {
        return majorVersion;
    }

    /**
     * 设置主版本
     * 
     * @param majorVersion
     *            主版本
     */
    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    /**
     * 获取次版本
     * 
     * @return minorVersion 次版本
     */
    public Integer getMinorVersion() {
        return minorVersion;
    }

    /**
     * 设置次版本
     * 
     * @param minorVersion
     *            次版本
     */
    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

    /**
     * 获取修订版本
     * 
     * @return patchVersion 修订版本
     */
    public Integer getPatchVersion() {
        return patchVersion;
    }

    /**
     * 设置修订版本
     * 
     * @param patchVersion
     *            修订版本
     */
    public void setPatchVersion(Integer patchVersion) {
        this.patchVersion = patchVersion;
    }

    /**
     * 获取编译版本
     * 
     * @return buildVersion 编译版本
     */
    public Integer getBuildVersion() {
        return buildVersion;
    }

    /**
     * 设置编译版本
     * 
     * @param buildVersion
     *            编译版本
     */
    public void setBuildVersion(Integer buildVersion) {
        this.buildVersion = buildVersion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ImageUpdateForm [type=" + type + ", majorVersion=" + majorVersion + ", minorVersion=" + minorVersion
                + ", patchVersion=" + patchVersion + ", buildVersion=" + buildVersion + "]";
    }

}
