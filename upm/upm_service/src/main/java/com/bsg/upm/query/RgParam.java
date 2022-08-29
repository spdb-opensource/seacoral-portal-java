package com.bsg.upm.query;

import java.io.Serializable;

public class RgParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long sanId;

    /**
     * 获取sanId
     * 
     * @return sanId sanId
     */
    public Long getSanId() {
        return sanId;
    }

    /**
     * 设置sanId
     * 
     * @param sanId
     *            sanId
     */
    public void setSanId(Long sanId) {
        this.sanId = sanId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RgParam [sanId=" + sanId + "]";
    }

}
