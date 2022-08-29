package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServUserDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模式
     */
    private String model;

    /**
     * 模式
     */
    private String modelDisplayName;

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
    private List<String> whiteIps = new ArrayList<>();

    /**
     * 白名单
     */
    private List<String> blackIps = new ArrayList<>();

    /**
     * 最小连接数
     */
    private Integer minConnectCnt;

    /**
     * 最大连接数
     */
    private Integer maxConnectCnt;

    /**
     * 数据库权限
     */
    private List<DBPrivilegeDTO> dbPrivileges = new ArrayList<>();

    /**
     * 获取模式
     * 
     * @return model 模式
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置模式
     * 
     * @param model
     *            模式
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取模式
     * 
     * @return modelDisplayName 模式
     */
    public String getModelDisplayName() {
        return modelDisplayName;
    }

    /**
     * 设置模式
     * 
     * @param modelDisplayName
     *            模式
     */
    public void setModelDisplayName(String modelDisplayName) {
        this.modelDisplayName = modelDisplayName;
    }

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
     * @return whiteIps 白名单
     */
    public List<String> getWhiteIps() {
        return whiteIps;
    }

    /**
     * 设置白名单
     * 
     * @param whiteIps
     *            白名单
     */
    public void setWhiteIps(List<String> whiteIps) {
        this.whiteIps = whiteIps;
    }

    /**
     * 获取白名单
     * 
     * @return blackIps 白名单
     */
    public List<String> getBlackIps() {
        return blackIps;
    }

    /**
     * 设置白名单
     * 
     * @param blackIps
     *            白名单
     */
    public void setBlackIps(List<String> blackIps) {
        this.blackIps = blackIps;
    }

    /**
     * 获取最小连接数
     * 
     * @return minConnectCnt 最小连接数
     */
    public Integer getMinConnectCnt() {
        return minConnectCnt;
    }

    /**
     * 设置最小连接数
     * 
     * @param minConnectCnt
     *            最小连接数
     */
    public void setMinConnectCnt(Integer minConnectCnt) {
        this.minConnectCnt = minConnectCnt;
    }

    /**
     * 获取最大连接数
     * 
     * @return maxConnectCnt 最大连接数
     */
    public Integer getMaxConnectCnt() {
        return maxConnectCnt;
    }

    /**
     * 设置最大连接数
     * 
     * @param maxConnectCnt
     *            最大连接数
     */
    public void setMaxConnectCnt(Integer maxConnectCnt) {
        this.maxConnectCnt = maxConnectCnt;
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
        return "ServUserDTO [model=" + model + ", modelDisplayName=" + modelDisplayName + ", authType=" + authType
                + ", authTypeDisplayName=" + authTypeDisplayName + ", username=" + username + ", whiteIps=" + whiteIps
                + ", blackIps=" + blackIps + ", minConnectCnt=" + minConnectCnt + ", maxConnectCnt=" + maxConnectCnt
                + ", dbPrivileges=" + dbPrivileges + "]";
    }

}
