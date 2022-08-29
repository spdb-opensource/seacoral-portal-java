package com.bsg.upm.query;

import java.io.Serializable;

public class UserParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组别编码
     */
    private String groupId;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 获取组别编码
     * 
     * @return groupId 组别编码
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置组别编码
     * 
     * @param groupId
     *            组别编码
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取是否可用
     * 
     * @return enabled 是否可用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     * 
     * @param enabled
     *            是否可用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserParam [groupId=" + groupId + ", enabled=" + enabled + "]";
    }

}
