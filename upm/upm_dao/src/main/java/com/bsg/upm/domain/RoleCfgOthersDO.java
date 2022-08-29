package com.bsg.upm.domain;

import java.io.Serializable;

/**
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleCfgOthersDO implements Serializable {

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
     * 工单自动审批
     */
    private Boolean orderAutoAudit;

    /**
     * 工单自动执行
     */
    private Boolean orderAutoExecute;

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
     * 获取工单自动审批
     * 
     * @return orderAutoAudit 工单自动审批
     */
    public Boolean getOrderAutoAudit() {
        return orderAutoAudit;
    }

    /**
     * 设置工单自动审批
     * 
     * @param orderAutoAudit
     *            工单自动审批
     */
    public void setOrderAutoAudit(Boolean orderAutoAudit) {
        this.orderAutoAudit = orderAutoAudit;
    }

    /**
     * @return the 工单自动执行
     */
    public Boolean getOrderAutoExecute() {
        return orderAutoExecute;
    }

    /**
     * @param 工单自动执行
     *            the orderAutoExecute to set
     */
    public void setOrderAutoExecute(Boolean orderAutoExecute) {
        this.orderAutoExecute = orderAutoExecute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgOthersDO [id=" + id + ", roleId=" + roleId + ", orderAutoAudit=" + orderAutoAudit
                + ", orderAutoExecute=" + orderAutoExecute + "]";
    }
}
