package com.bsg.upm.query;

import java.io.Serializable;

public class AreaParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 可用状态
     */
    private Boolean enabled;

    /**
     * 获取可用状态
     * 
     * @return enabled 可用状态
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置可用状态
     * 
     * @param enabled
     *            可用状态
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AreaParam [enabled=" + enabled + "]";
    }

}