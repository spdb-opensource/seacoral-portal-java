package com.bsg.upm.form;

import java.io.Serializable;

public class DatabaseForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 字符集
     */
    private String characterSet;

    /**
     * 获取名称
     * 
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *            名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取字符集
     * 
     * @return characterSet 字符集
     */
    public String getCharacterSet() {
        return characterSet;
    }

    /**
     * 设置字符集
     * 
     * @param characterSet
     *            字符集
     */
    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DatabaseForm [name=" + name + ", characterSet=" + characterSet + "]";
    }

}
