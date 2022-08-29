package com.bsg.upm.dto;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleCfgOthersDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色
     */
    public RoleDTO role;

    /**
     * 工单自动审批
     */
    public Boolean ogAutoAudit;

    /**
     * 工单自动执行
     */
    public Boolean ogAutoExecute;

    public class RoleDTO {
        /**
         * 角色编码
         */
        private String id;

        /**
         * 角色名称
         */
        private String name;

        /**
         * 获取 角色编码
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置角色编码
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 角色名称
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置角色名称
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "RoleDTO [id=" + id + ", name=" + name + "]";
        }
    }

    /**
     * 获取 角色
     * 
     * @return role
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * 设置角色
     * 
     * @param role
     */
    public void setRole(RoleDTO role) {
        this.role = role;
    }

    /**
     * 设置工单自动审批
     * 
     * @param ogAutoAudit
     */
    public void setOgAutoAudit(Boolean ogAutoAudit) {
        this.ogAutoAudit = ogAutoAudit;
    }

    /**
     * 设置工单自动执行
     * 
     * @param ogAutoExecute
     */
    public void setOgAutoExecute(Boolean ogAutoExecute) {
        this.ogAutoExecute = ogAutoExecute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgOthersDTO [role=" + role + ", ogAutoAudit=" + ogAutoAudit + ", ogAutoExecute=" + ogAutoExecute
                + "]";
    }

}
