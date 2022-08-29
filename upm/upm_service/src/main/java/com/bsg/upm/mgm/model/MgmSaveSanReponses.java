package com.bsg.upm.mgm.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmSaveSanReponses implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    /**
     * 获取id
     * 
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     * 
     * @param id
     *            id
     */
    public void setId(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmSaveSanReponses [id=" + id + "]";
    }

}
