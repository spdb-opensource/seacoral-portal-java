package com.bsg.upm.form;

import java.io.Serializable;

public class UnitRebuildForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String hostRelateId;

    public String getHostRelateId() {
        return hostRelateId;
    }

    public void setHostRelateId(String hostRelateId) {
        this.hostRelateId = hostRelateId;
    }

    @Override
    public String toString() {
        return "UnitRebuildForm [hostRelateId=" + hostRelateId + "]";
    }

}
