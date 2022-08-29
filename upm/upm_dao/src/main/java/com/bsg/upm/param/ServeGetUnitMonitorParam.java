package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServeGetUnitMonitorParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "relateCode")
    private String relateCode;
    
    @JSONField(name = "description")
    private String description;

	public String getRelateCode() {
		return relateCode;
	}
	

	public void setRelateCode(String relateCode) {
		this.relateCode = relateCode;
	}
	

	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "ServeGetUnitMonitorParam [relateCode=" + relateCode + ", description=" + description + "]";
	}
	
	
	

   
}
