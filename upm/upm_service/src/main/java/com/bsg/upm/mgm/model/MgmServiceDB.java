package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmServiceDB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "characterSet")
    private String characterSet;

    @JSONField(name = "tables")
    private List<Table> tables;

    /**
     * 获取name
     * 
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取characterSet
     * 
     * @return characterSet characterSet
     */
    public String getCharacterSet() {
        return characterSet;
    }

    /**
     * 设置characterSet
     * 
     * @param characterSet
     *            characterSet
     */
    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    /**
     * 获取tables
     * 
     * @return tables tables
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * 设置tables
     * 
     * @param tables
     *            tables
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmServiceDB [name=" + name + ", characterSet=" + characterSet + ", tables=" + tables + "]";
    }

    public static class Table {
        @JSONField(name = "name")
        private String name;

        @JSONField(name = "size")
        private Long size;

        /**
         * 获取name
         * 
         * @return name name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置name
         * 
         * @param name
         *            name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取size
         * 
         * @return size size
         */
        public Long getSize() {
            return size;
        }

        /**
         * 设置size
         * 
         * @param size
         *            size
         */
        public void setSize(Long size) {
            this.size = size;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Table [name=" + name + ", size=" + size + "]";
        }

    }

}
