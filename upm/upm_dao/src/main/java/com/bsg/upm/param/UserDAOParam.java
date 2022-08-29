package com.bsg.upm.param;

import java.io.Serializable;

public class UserDAOParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private Long roleId;

    /**
     * 组别编码
     */
    private Long groupId;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 获取角色编码
     * 
     * @return roleId 角色编码
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编码
     * 
     * @param roleId
     *            角色编码
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取组别编码
     * 
     * @return groupId 组别编码
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置组别编码
     * 
     * @param groupId
     *            组别编码
     */
    public void setGroupId(Long groupId) {
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
        return "UserDAOParam [roleId=" + roleId + ", groupId=" + groupId + ", enabled=" + enabled + "]";
    }

}
