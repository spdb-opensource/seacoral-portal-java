package com.bsg.upm.form;

import java.io.Serializable;

public class AuditForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状态
     */
    private String status;

    /**
     * 审批信息
     */
    private String msg;

    /**
     * 获取状态
     * 
     * @return status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     * 
     * @param status
     *            状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取审批信息
     * 
     * @return msg 审批信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置审批信息
     * 
     * @param msg
     *            审批信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AuditForm [status=" + status + ", msg=" + msg + "]";
    }

}
