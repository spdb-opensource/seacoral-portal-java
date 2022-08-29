package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServMonitorCancelParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "del_container")
    private Boolean delContainer;
	public Boolean getDelContainer() {
		return delContainer;
	}
	
	public void setDelContainer(Boolean delContainer) {
		this.delContainer = delContainer;
	}

	@Override
	public String toString() {
		return "ServMonitorCancelParam [delContainer=" + delContainer + "]";
	}
	
    

   
}
