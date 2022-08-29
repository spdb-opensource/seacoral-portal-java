package com.bsg.upm.domain;

import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年5月17日
 */
public class ServGroupAppDO implements Serializable {

	 /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    
    /**
     * 
     */
    private String relateId;
    /**
     * 
     */
    private String businessSubsystemId;
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRelateId() {
		return relateId;
	}
	
	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	
	public String getBusinessSubsystemId() {
		return businessSubsystemId;
	}
	
	public void setBusinessSubsystemId(String businessSubsystemId) {
		this.businessSubsystemId = businessSubsystemId;
	}

	@Override
	public String toString() {
		return "ServGroupAppDO [id=" + id + ", relateId=" + relateId + ", businessSubsystemId=" + businessSubsystemId
				+ "]";
	}
	
    
    
}
