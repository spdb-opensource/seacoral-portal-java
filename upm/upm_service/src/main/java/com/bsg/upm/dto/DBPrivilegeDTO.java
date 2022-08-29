package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBPrivilegeDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String dbName;

    /**
     * 数据库权限
     */
    private List<String> privileges = new ArrayList<>();

    /**
     * 获取
     * 
     * @return dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * 设置
     * 
     * @param dbName
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * 获取数据库权限
     * 
     * @return privileges 数据库权限
     */
    public List<String> getPrivileges() {
        return privileges;
    }

    /**
     * 设置数据库权限
     * 
     * @param privileges
     *            数据库权限
     */
    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBPrivilegeDTO [dbName=" + dbName + ", privileges=" + privileges + "]";
    }

}
