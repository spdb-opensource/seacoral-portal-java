package com.bsg.upm.dto;

import java.io.Serializable;

public class KeysetDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Boolean canSet;

    private Boolean mustRestart;

    private String key;

    private String value;

    private String defaultValue;

    private String description;

    private String range;

    /**
     * 获取canSet
     * 
     * @return canSet canSet
     */
    public Boolean getCanSet() {
        return canSet;
    }

    /**
     * 设置canSet
     * 
     * @param canSet
     *            canSet
     */
    public void setCanSet(Boolean canSet) {
        this.canSet = canSet;
    }

    /**
     * 获取mustRestart
     * 
     * @return mustRestart mustRestart
     */
    public Boolean getMustRestart() {
        return mustRestart;
    }

    /**
     * 设置mustRestart
     * 
     * @param mustRestart
     *            mustRestart
     */
    public void setMustRestart(Boolean mustRestart) {
        this.mustRestart = mustRestart;
    }

    /**
     * 获取key
     * 
     * @return key key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key
     * 
     * @param key
     *            key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取value
     * 
     * @return value value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value
     * 
     * @param value
     *            value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取defaultValue
     * 
     * @return defaultValue defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置defaultValue
     * 
     * @param defaultValue
     *            defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 获取description
     * 
     * @return description description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description
     * 
     * @param description
     *            description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取range
     * 
     * @return range range
     */
    public String getRange() {
        return range;
    }

    /**
     * 设置range
     * 
     * @param range
     *            range
     */
    public void setRange(String range) {
        this.range = range;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "KeysetDTO [canSet=" + canSet + ", mustRestart=" + mustRestart + ", key=" + key + ", value=" + value
                + ", defaultValue=" + defaultValue + ", description=" + description + ", range=" + range + "]";
    }

}
