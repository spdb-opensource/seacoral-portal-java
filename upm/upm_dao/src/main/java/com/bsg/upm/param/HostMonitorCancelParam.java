package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class HostMonitorCancelParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "force")
    private Boolean force;
    
	public Boolean getForce() {
		return force;
	}
	
	public void setForce(Boolean force) {
		this.force = force;
	}

	@Override
	public String toString() {
		return "HostMonitorCancelParam [force=" + force + "]";
	}
	
}
