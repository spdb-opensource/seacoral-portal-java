package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BackupForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 备份文件保留天数
     */
    private Integer retention;

    /**
     * 表名 Map(key:库名, value:表名集合)
     */
    private Map<String, List<String>> tables;

    public BackupForm() {
        this.retention = 7;
    }

    /**
     * 获取类型
     * 
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type
     *            类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取备份文件保留天数
     * 
     * @return retention 备份文件保留天数
     */
    public Integer getRetention() {
        return retention;
    }

    /**
     * 设置备份文件保留天数
     * 
     * @param retention
     *            备份文件保留天数
     */
    public void setRetention(Integer retention) {
        this.retention = retention;
    }

    /**
     * 获取表名Map(key:库名value:表名集合)
     * 
     * @return tables 表名Map(key:库名value:表名集合)
     */
    public Map<String, List<String>> getTables() {
        return tables;
    }

    /**
     * 设置表名Map(key:库名value:表名集合)
     * 
     * @param tables
     *            表名Map(key:库名value:表名集合)
     */
    public void setTables(Map<String, List<String>> tables) {
        this.tables = tables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BackupForm [type=" + type + ", retention=" + retention + ", tables=" + tables + "]";
    }

}
