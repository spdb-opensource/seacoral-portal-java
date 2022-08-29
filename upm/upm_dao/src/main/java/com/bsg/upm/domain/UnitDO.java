package com.bsg.upm.domain;

import java.io.Serializable;

public class UnitDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String servId;
    private String type;
    private String relateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelateId() {
        return relateId;
    }

    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    @Override
    public String toString() {
        return "UnitDO [id=" + id + ", servId=" + servId + ", type=" + type + ", relateId=" + relateId + "]";
    }

}
