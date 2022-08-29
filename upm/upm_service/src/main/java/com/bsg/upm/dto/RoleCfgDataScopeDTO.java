package com.bsg.upm.dto;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleCfgDataScopeDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 角色
     */
    public RoleDTO role;
    
    /**
     * 工单组可见数据范围
     */
    public ScopeDTO ogScope;
    
    /**
     * 服务组可见数据范围
     */
    public ScopeDTO sgScope;
    
    /**
     * 操作日志可见数据范围
     */
    public ScopeDTO operateLogScope;
    
    
    public class RoleDTO{
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


        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "RoleDTO [id=" + id + ", name=" + name + "]";
        }
        
    }
    
     public class ScopeDTO{
         /**
          * 数据可见范围
          */
         private String code;

         /**
          * 数据可见范围展示名称
          */
         private String display;

        /**
         * 获取 数据可见范围
         * 
         * @return code
         */
        public String getCode() {
            return code;
        }
        

        /**
         * 设置数据可见范围
         * 
         * @param code
         */
        public void setCode(String code) {
            this.code = code;
        }
        

        /**
         * 获取 数据可见范围展示名称
         * 
         * @return display
         */
        public String getDisplay() {
            return display;
        }
        

        /**
         * 设置数据可见范围展示名称
         * 
         * @param display
         */
        public void setDisplay(String display) {
            this.display = display;
        }


        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ScopeDTO [code=" + code + ", display=" + display + "]";
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
     * 获取 工单组可见数据范围
     * 
     * @return ogScope
     */
    public ScopeDTO getOgScope() {
        return ogScope;
    }
    
    /**
     * 设置工单组可见数据范围
     * 
     * @param ogScope
     */
    public void setOgScope(ScopeDTO ogScope) {
        this.ogScope = ogScope;
    }
    
    /**
     * 获取 服务组可见数据范围
     * 
     * @return sgScope
     */
    public ScopeDTO getSgScope() {
        return sgScope;
    }
    
    /**
     * 设置服务组可见数据范围
     * 
     * @param sgScope
     */
    public void setSgScope(ScopeDTO sgScope) {
        this.sgScope = sgScope;
    }
    
    /**
     * 获取 操作日志可见数据范围
     * 
     * @return operateLogScope
     */
    public ScopeDTO getOperateLogScope() {
        return operateLogScope;
    }
    
    /**
     * 设置操作日志可见数据范围
     * 
     * @param operateLogScope
     */
    public void setOperateLogScope(ScopeDTO operateLogScope) {
        this.operateLogScope = operateLogScope;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgDataScopeDTO [role=" + role + ", ogScope=" + ogScope + ", sgScope=" + sgScope
                + ", operateLogScope=" + operateLogScope + "]";
    }
    
    
}
