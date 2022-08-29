package com.bsg.upm.form;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleCfgDataScopeForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 工单数据可见范围
     */
    private String ogScopeCode;

    /**
     * 服务数据可见范围
     */
    private String sgScopeCode;

    /**
     * 操作日志数据可见范围
     */
    private String operateLogScope;

    /**
     * 获取 工单数据可见范围
     * 
     * @return ogScopeCode
     */
    public String getOgScopeCode() {
        return ogScopeCode;
    }

    /**
     * 设置工单数据可见范围
     * 
     * @param ogScopeCode
     */
    public void setOgScopeCode(String ogScopeCode) {
        this.ogScopeCode = ogScopeCode;
    }

    /**
     * 获取 服务数据可见范围
     * 
     * @return sgScopeCode
     */
    public String getSgScopeCode() {
        return sgScopeCode;
    }

    /**
     * 设置服务数据可见范围
     * 
     * @param sgScopeCode
     */
    public void setSgScopeCode(String sgScopeCode) {
        this.sgScopeCode = sgScopeCode;
    }

    /**
     * 获取 操作日志数据可见范围
     * 
     * @return operateLogScope
     */
    public String getOperateLogScope() {
        return operateLogScope;
    }

    /**
     * 设置操作日志数据可见范围
     * 
     * @param operateLogScope
     */
    public void setOperateLogScope(String operateLogScope) {
        this.operateLogScope = operateLogScope;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleCfgDataScopeForm [ogScopeCode=" + ogScopeCode + ", sgScopeCode=" + sgScopeCode
                + ", operateLogScope=" + operateLogScope + "]";
    }
}
