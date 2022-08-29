package com.bsg.upm.form;

import java.io.Serializable;

public class MigrateStartForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    private String password;

    /**
     * 获取密码
     * 
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * 
     * @param password
     *            密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MigrateStartForm [password=" + password + "]";
    }

}
