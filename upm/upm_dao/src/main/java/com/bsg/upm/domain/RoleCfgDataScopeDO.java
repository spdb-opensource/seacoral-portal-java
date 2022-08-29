package com.bsg.upm.domain;

import java.io.Serializable;

/**
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleCfgDataScopeDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;

    /**
     * 角色编码
     */
    private String roleId;

    /**
     * 工单数据可见范围
     */
    private String orderGroup;

    /**
     * 服务数据可见范围
     */
    private String servGroup;

    /**
     * 操作日志数据可见范围
     */
    private String operateLog;

    /**
     * 获取自增主键
     * 
     * @return id 自增主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置自增主键
     * 
     * @param id
     *            自增主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取角色编码
     * 
     * @return roleId 角色编码
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编码
     * 
     * @param roleId
     *            角色编码
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取工单数据可见范围
     * 
     * @return orderGroup 工单数据可见范围
     */
    public String getOrderGroup() {
        return orderGroup;
    }

    /**
     * 设置工单数据可见范围
     * 
     * @param orderGroup
     *            工单数据可见范围
     */
    public void setOrderGroup(String orderGroup) {
        this.orderGroup = orderGroup;
    }

    /**
     * 获取服务数据可见范围
     * 
     * @return servGroup 服务数据可见范围
     */
    public String getServGroup() {
        return servGroup;
    }

    /**
     * 设置服务数据可见范围
     * 
     * @param servGroup
     *            服务数据可见范围
     */
    public void setServGroup(String servGroup) {
        this.servGroup = servGroup;
    }

    /**
     * 获取操作日志数据可见范围
     * 
     * @return operateLog 操作日志数据可见范围
     */
    public String getOperateLog() {
        return operateLog;
    }

    /**
     * 设置操作日志数据可见范围
     * 
     * @param operateLog
     *            操作日志数据可见范围
     */
    public void setOperateLog(String operateLog) {
        this.operateLog = operateLog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgDataScopeDO [id=" + id + ", roleId=" + roleId + ", orderGroup=" + orderGroup + ", servGroup="
                + servGroup + ", operateLog=" + operateLog + "]";
    }
}
