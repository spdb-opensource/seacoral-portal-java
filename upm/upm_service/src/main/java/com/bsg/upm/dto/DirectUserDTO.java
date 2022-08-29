package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DirectUserDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 认证方式
     */
    private String authType;

    /**
     * 认证方式
     */
    private String authTypeDisplayName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 白名单
     */
    private String whiteIp;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 数据库权限
     */
    private List<DBPrivilegeDTO> dbPrivileges = new ArrayList<>();

    /**
     * 获取认证方式
     * 
     * @return authType 认证方式
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * 设置认证方式
     * 
     * @param authType
     *            认证方式
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * 获取认证方式
     * 
     * @return authTypeDisplayName 认证方式
     */
    public String getAuthTypeDisplayName() {
        return authTypeDisplayName;
    }

    /**
     * 设置认证方式
     * 
     * @param authTypeDisplayName
     *            认证方式
     */
    public void setAuthTypeDisplayName(String authTypeDisplayName) {
        this.authTypeDisplayName = authTypeDisplayName;
    }

    /**
     * 获取用户名
     * 
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * 
     * @param username
     *            用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取白名单
     * 
     * @return whiteIp 白名单
     */
    public String getWhiteIp() {
        return whiteIp;
    }

    /**
     * 设置白名单
     * 
     * @param whiteIp
     *            白名单
     */
    public void setWhiteIp(String whiteIp) {
        this.whiteIp = whiteIp;
    }

    /**
     * 获取创建时间
     * 
     * @return gmtCreate 创建时间
     */
    public String getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     * 
     * @param gmtCreate
     *            创建时间
     */
    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取数据库权限
     * 
     * @return dbPrivileges 数据库权限
     */
    public List<DBPrivilegeDTO> getDbPrivileges() {
        return dbPrivileges;
    }

    /**
     * 设置数据库权限
     * 
     * @param dbPrivileges
     *            数据库权限
     */
    public void setDbPrivileges(List<DBPrivilegeDTO> dbPrivileges) {
        this.dbPrivileges = dbPrivileges;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DirectUserDTO [authType=" + authType + ", authTypeDisplayName=" + authTypeDisplayName + ", username="
                + username + ", whiteIp=" + whiteIp + ", gmtCreate=" + gmtCreate + ", dbPrivileges=" + dbPrivileges
                + "]";
    }

}
