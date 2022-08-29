package com.bsg.upm.form;

import java.io.Serializable;

public class ParameterCfgForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String value;

    /**
     * 获取
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParameterCfgForm [name=" + name + ", value=" + value + "]";
    }

}
