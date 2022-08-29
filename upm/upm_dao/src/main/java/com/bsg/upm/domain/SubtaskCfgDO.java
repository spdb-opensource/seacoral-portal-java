package com.bsg.upm.domain;

import java.io.Serializable;

public class SubtaskCfgDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String objType;
    private String actionType;
    private Long timeout;
    private String description;

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubtaskCfgDO [objType=" + objType + ", actionType=" + actionType + ", timeout=" + timeout
                + ", description=" + description + "]";
    }

}
