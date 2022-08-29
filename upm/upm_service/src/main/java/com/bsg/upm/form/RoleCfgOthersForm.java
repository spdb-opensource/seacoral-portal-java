package com.bsg.upm.form;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月2日
 */
public class RoleCfgOthersForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 工单是否自动审批
     */
    private Boolean ogAutoAudit;

    /**
     * 工单是否自动执行
     */
    private Boolean ogAutoExecute;

    /**
     * 获取 工单是否自动审批
     * 
     * @return ogAutoAudit
     */
    public Boolean getOgAutoAudit() {
        return ogAutoAudit;
    }

    /**
     * 设置工单是否自动审批
     * 
     * @param ogAutoAudit
     */
    public void setOgAutoAudit(Boolean ogAutoAudit) {
        this.ogAutoAudit = ogAutoAudit;
    }

    /**
     * 获取 工单是否自动执行
     * 
     * @return ogAutoExecute
     */
    public Boolean getOgAutoExecute() {
        return ogAutoExecute;
    }

    /**
     * 设置工单是否自动执行
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
        return "RoleCfgOthersForm [ogAutoAudit=" + ogAutoAudit + ", ogAutoExecute=" + ogAutoExecute + "]";
    }

}
