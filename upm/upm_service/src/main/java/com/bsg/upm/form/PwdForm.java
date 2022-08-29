package com.bsg.upm.form;

import java.io.Serializable;

public class PwdForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 原密码
     */
    private String originalPwd;

    /**
     * 新密码
     */
    private String newPwd;

    /**
     * 获取原密码
     * 
     * @return originalPwd 原密码
     */
    public String getOriginalPwd() {
        return originalPwd;
    }

    /**
     * 设置原密码
     * 
     * @param originalPwd
     *            原密码
     */
    public void setOriginalPwd(String originalPwd) {
        this.originalPwd = originalPwd;
    }

    /**
     * 获取新密码
     * 
     * @return newPwd 新密码
     */
    public String getNewPwd() {
        return newPwd;
    }

    /**
     * 设置新密码
     * 
     * @param newPwd
     *            新密码
     */
    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PwdForm [originalPwd=" + originalPwd + ", newPwd=" + newPwd + "]";
    }

}
